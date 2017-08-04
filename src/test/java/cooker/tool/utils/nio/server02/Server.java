package cooker.tool.utils.nio.server02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yu.kequn on 2017/8/3.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9999));
        server.configureBlocking(false);
        Selector selector = Selector.open();

        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            int ready = selector.select();
            if (ready == 0) continue;
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it!=null && it.hasNext()){
                SelectionKey key = it.next();
                if(key.isAcceptable()){
                    System.out.println("接收到连接请求...");
                    server = (ServerSocketChannel)key.channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);
                }
                if(key.isReadable()){
                    System.out.println("可以读取....");
                    SocketChannel ch = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    StringBuilder sb = new StringBuilder();
                    while (ch.read(buffer) > 0){
                        buffer.flip();
                        byte[] b = new byte[buffer.limit()];
                        buffer.get(b,buffer.position(),buffer.limit());
                        sb.append(new String (b));
                        buffer.clear();
                    }
                    System.out.println("消息：" + sb.toString());
                    ch.register(selector, SelectionKey.OP_WRITE);
                }else if(key.isWritable()){
                    System.out.println("可以写入....");
                    SocketChannel ch = (SocketChannel) key.channel();
//                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    buffer.put(new String("<body>hello world</body>").getBytes());
//                    ch.write(buffer);
                    ch.close();
                }
                it.remove();
            }
        }
    }
}
