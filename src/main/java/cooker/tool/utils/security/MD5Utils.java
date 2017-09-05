package cooker.tool.utils.security;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * Created by yu.kequn on 2017/8/28.
 */
public class MD5Utils {

    public static String EncryptByHexBit32(String sSrc) throws Exception {
        return DigestUtils.md5Hex(sSrc);
    }

    public static String EncryptByHexBit16(String sSrc) throws Exception {
        return EncryptByHexBit32(sSrc).substring(8, 24);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(MD5Utils.EncryptByHexBit32("aa"));
    }
}
