package cooker.tool.utils.security;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yu.kequn on 2017/8/11.
 */
public class GZipUtils {
    public static final String UTF_8 = "UTF-8";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String GBK = "GBK";

    // 压缩
    public static byte[] gzip(byte[] bytes) throws IOException {
        if(ArrayUtils.isEmpty(bytes)) return null;
        byte[] bs = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(bytes);
        gzip.flush();
        gzip.close();
        bs = bos.toByteArray();
        bos.close();
        return bs;
    }

    // 压缩
    public static byte[] gzip(String str) throws IOException {
        if(StringUtils.isEmpty(str)) return null;
        return gzip(str, UTF_8);
    }

    // 压缩
    public static byte[] gzip(String str, String encoding) throws IOException {
        if(StringUtils.isEmpty(str)) return null;
        return gzip(str.getBytes(encoding));
    }

    // 解压缩
    public static byte[] ungzip(byte[] bytes) throws IOException {
        if(ArrayUtils.isEmpty(bytes)) return null;
        byte[] bs = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        GZIPInputStream gzip = new GZIPInputStream(bis);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gzip.read(buffer)) != -1) {
            bos.write(buffer, 0, n);
        }
        gzip.close();
        bis.close();
        bs = bos.toByteArray();
        bos.flush();
        bos.close();
        return bs;
    }

    public static String ungzip2str(byte[] bytes) throws IOException {
        return IOUtils.toString(ungzip(bytes), UTF_8);
    }

    public static String ungzip2str(byte[] bytes, String encoding) throws IOException {
        return IOUtils.toString(ungzip(bytes), encoding);
    }

}