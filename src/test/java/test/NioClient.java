package test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by cooker on 2017/8/30.
 */
public class NioClient extends INio {

    @Override
    public void init() throws Exception{
        super.selector = Selector.open();
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(super.HOST, super.PORT));
        channel.register(super.selector, SelectionKey.OP_CONNECT);
    }

    @Override
    public void start() throws Exception {
        while (true){
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isConnectable()){
                    connect(key);
                }else if(key.isReadable()){
                    read(key);
                }
            }
        }
    }

    @Override
    public void read(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        String response = new String(buffer.array()).trim();
        System.out.println("服务器响应：" + response);
        System.out.println("客户端发送消息>>：");
        ByteBuffer outBuffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
        String sres = scanner.nextLine();
        channel.write(outBuffer);
    }

    @Override
    public void connect(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        if(channel.isConnectionPending()){
            if(channel.finishConnect()){
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
                System.out.println("客户端发送消息：");
                String request = scanner.nextLine();
                channel.write(ByteBuffer.wrap(request.getBytes()));
            }else {
                key.cancel();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        NioClient client = new NioClient();
        client.init();
        client.start();
    }
}
