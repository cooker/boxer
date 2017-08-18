package cooker.tool.utils.security;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yu.kequn on 2017/8/18.
 */
public class AESUtils {
    private AESUtils self;
    private static final int DEFAULT_KEY_LENGTH =  16;
    private String type; //例如：AES/ECB/PKCS5Padding
    private AESUtils(){}

    public static AESUtils newInstance(String type){
        AESUtils self = new AESUtils();
        self.self = self;
        self.type = type;
        return self;
    }

    public byte[] Encrypt(byte[] bSrc, String sKey) throws Exception {
        return Encrypt(bSrc, sKey, DEFAULT_KEY_LENGTH);
    }

    // 加密
    public byte[] Encrypt(byte[] bSrc, String sKey, int lkey) throws Exception {
        String snKey = getValidsKey(sKey, lkey);
        byte[] raw = snKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(type);//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(bSrc);
        return encrypted;
    }

    public byte[] Decrypt(byte[] bSrc, String sKey) throws Exception {
        return Decrypt(bSrc, sKey, DEFAULT_KEY_LENGTH);
    }

    // 解密
    public byte[] Decrypt(byte[] bSrc, String sKey, int lkey) throws Exception {
        String snKey = getValidsKey(sKey, lkey);
        byte[] raw = snKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance(type);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] original = cipher.doFinal(bSrc);
        return original;
    }

    //获取有效的key
    protected String getValidsKey(String sKey, int lkey){
        String nKey = StringUtils.trimToEmpty(sKey);
        if(lkey <= 0) throw new RuntimeException("AES加密key长度不能小于0");
        int llen = nKey.length();
        if(llen >= lkey)
            return nKey.substring(0, lkey);
        else{
            StringBuilder sb = new StringBuilder(nKey);
            llen = lkey - llen;
            while (llen -- > 0)  sb.append("0");
            return sb.toString();
        }
    }

}
