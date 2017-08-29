package http;

import com.google.common.collect.Maps;
import cooker.tool.utils.http.HttpUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by yu.kequn on 2017/8/29.
 */
public class HttpTest {
    @Test
    public void doPostFormData() throws IOException {
        String url = "http://127.0.0.1:8080/simpleWeb/index.jsp?";
        Map<String, ContentBody> params = Maps.newHashMap();
        params.put("xxx",new StringBody("a", StandardCharsets.UTF_8));
        String sou =  HttpUtils.doPostFormData(url, params);
        System.out.println(sou);
    }

    @Test
    public void doPostForm() throws IOException {
        String url = "http://127.0.0.1:8080/simpleWeb/index.jsp?";
        Map<String, String> params = Maps.newHashMap();
        params.put("xxx","xxx");
        String sou =  HttpUtils.doPostForm(url, params);
        System.out.println(sou);
    }

    @Test
    public void doGet() throws IOException {
        String url = "http://127.0.0.1:8080/simpleWeb/index.jsp?";
        Map<String, String> params = Maps.newHashMap();
        params.put("xxx","xxx");
        String sou =  HttpUtils.doGet(url, params);
        System.out.println(sou);
    }

    @Test
    public void doPostJson() throws Exception {
        String url = "http://127.0.0.1:8080/simpleWeb/index.jsp?";
        Map<String, String> params = Maps.newHashMap();
        params.put("xxx","xxx");
        String sou =  HttpUtils.doPostJson(url, params);
        System.out.println(sou);
    }


}
