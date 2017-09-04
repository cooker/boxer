package test;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Scanner;

/**
 * Created by cooker on 2017/8/30.
 */
public abstract class INio {
    public static final String HOST = "127.0.0.1";
    public static int PORT = 7080;

    protected Selector selector;
    protected Scanner scanner = new Scanner(System.in);

    abstract void init() throws Exception;

    abstract void start() throws Exception;

    abstract void read(SelectionKey key) throws  Exception;

    void connect(SelectionKey key) throws  Exception{}

    void accept(SelectionKey key) throws  Exception{}
}
