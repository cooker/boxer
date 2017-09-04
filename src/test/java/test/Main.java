package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumSet;
import java.util.Scanner;

/**
 * Created by yu.kequn on 2017/8/23.
 */
public class Main {

    @Test
    public void client() throws Exception {
        NioClient client = new NioClient();
        client.init();
        client.start();
    }

    @Test
    public void server() throws Exception {
        NioServer server = new NioServer();
        server.init();
        
        server.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(":::");
        String sss = reader.readLine();
        System.out.println(sss);
    }
}
