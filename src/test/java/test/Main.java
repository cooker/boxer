package test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.util.EnumSet;

/**
 * Created by yu.kequn on 2017/8/23.
 */
public class Main {
    @Test
    public void test(){
        System.out.println(DigestUtils.sha256Hex("aa"));
    }
}
