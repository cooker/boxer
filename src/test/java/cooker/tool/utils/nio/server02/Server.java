package cooker.tool.utils.nio.server02;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yu.kequn on 2017/8/3.
 */
public class Server {
    private int ports[];
    private ByteBuffer echoBuffer = ByteBuffer.allocate( 1024 );

    public Server( int ports[] ) throws IOException {
        this.ports = ports;
        configure_selector();
    }

    private void configure_selector() throws IOException {
        // Create a new selector
        Selector selector = Selector.open();
        // Open a listener on each port, and register each one
        // with the selector
        for (int i=0; i<ports.length; ++i) {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket ss = ssc.socket();
            InetSocketAddress address = new InetSocketAddress(ports[i]);
            ss.bind(address);
            SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Going to listen on " + ports[i]);
        }

        while (true) {
            int num = selector.select();
            if(num == 0 )continue;
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                it.remove();
                if (key.isAcceptable()) {
                    // Accept the new connection
                    ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    // Add the new connection to the selector
                    SelectionKey newKey = sc.register(selector, SelectionKey.OP_READ);
                    System.out.println( "Got connection from "+sc );
                } else if (key.isReadable()) {
                    // Read the data
                    SocketChannel sc = (SocketChannel)key.channel();
                    // Echo data
                    int bytesEchoed = 0;
                    while (true) {
                        echoBuffer.clear();
                        int number_of_bytes = sc.read(echoBuffer);
                        if (number_of_bytes <= 0) {
                            break;
                        }
                        echoBuffer.flip();
                        sc.write(echoBuffer);
                        bytesEchoed += number_of_bytes;
                    }
                    sc.close();
                    System.out.println("Echoed " + bytesEchoed + " from " + sc);
                }

            }
        }
    }

    static public void main( String args[] ) throws Exception {
        int ports[] = new int[]{8080};

        new Server(ports);
    }
}