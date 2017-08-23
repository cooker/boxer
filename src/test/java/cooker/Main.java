package cooker;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yu.kequn on 2017/8/14.
 */
public class Main {
    public static void main(String[] args) {
        StringJoiner str = new StringJoiner(",","x","7");
        str.add("1111");
        str.add("6666");
        str.add("6666");
        System.out.println(str);
    }
}
