package test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by cooker on 2017/8/30.
 */
public class NioServer extends INio{
    @Override
    public void init() throws Exception {
        selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind( new InetSocketAddress(super.PORT));
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    @Override
    public void start() throws Exception {
        while (true){
            selector.select();
            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();
            while (ite.hasNext()){
                SelectionKey key = ite.next();
                ite.remove();
                if(key.isAcceptable()){
                    accept(key);
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
        System.out.println("客户端响应：" + response);
        System.out.println("服务器发送消息>>：");
        ByteBuffer outBuffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
        String sres = scanner.nextLine();
        channel.write(outBuffer);

    }

    @Override
    public void accept(SelectionKey key) throws Exception {
        ServerSocketChannel server = (ServerSocketChannel) key.channel();
        SocketChannel channel = server.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);
    }

    public static void main(String[] args) throws Exception {
        NioServer server = new NioServer();
        server.init();
        server.start();
    }
}
