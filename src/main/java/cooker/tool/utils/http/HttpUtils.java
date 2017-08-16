package cooker.tool.utils.http;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cooker.tool.utils.json.JacksonUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
/**
 * Created by yu.kequn on 2017/8/10.
 */
public class HttpUtils {
    public static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    protected HttpUtils(){}

    public static String doGet(String url) throws IOException {
        return doGet(url, null);
    }

    public static String doGet(String url, Map<String, String> params) throws IOException {
        return doGet(url, null, HTTP.UTF_8);
    }

    public static String doGet(String url, Map<String, String> params, String encoding)
            throws IOException {
        String result = HttpResponse.FAIL.toString();

        try (CloseableHttpClient client = HttpClients.createDefault()){

            String nurl = StringUtils.trimToNull(url);
            String uparams = newGetParams(params);
            StringBuilder sb = new StringBuilder(nurl);
            boolean cs = false, equ = false;
            cs = nurl.contains("?");
            equ = nurl.contains("=");
            if(cs && equ) sb.append("&");
                else if(equ) sb.append("?");
            sb.append(uparams);

            HttpGet get = new HttpGet(sb.toString());
            CloseableHttpResponse httpResponse = client.execute(get);
            HttpEntity entity = httpResponse.getEntity();
            if(Objects.nonNull(entity)){
                result = IOUtils.toString(entity.getContent(), encoding);
            }
            HttpClientUtils.closeQuietly(httpResponse);
        } catch (IOException e) {
            throw new IOException("HTTP doGet IO Exception", e);
        }
        return result;
    }

    public static String doPostForm(String url, Map<String, String> params) throws IOException {
        return doPostForm(url, params, HTTP.UTF_8);
    }

    public static String doPostForm(String url, Map<String, String> params, String encoding)
            throws IOException {
        String result = HttpResponse.FAIL.toString();

        try (CloseableHttpClient client = HttpClients.createDefault()){
            List<NameValuePair> nvs = Lists.newArrayList();
            String nurl = StringUtils.trimToNull(url);
            if(MapUtils.isNotEmpty(params))
                params.forEach((key, value)->{
                    NameValuePair nv = new BasicNameValuePair(key, value);
                    nvs.add(nv);
                });
            HttpPost post = new HttpPost(nurl);
            post.setEntity(new UrlEncodedFormEntity(nvs, encoding));

            CloseableHttpResponse httpResponse = client.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            if(Objects.nonNull(entity)){
                result = EntityUtils.toString(entity, encoding);
            }
            HttpClientUtils.closeQuietly(httpResponse);
        } catch (IOException e) {
            throw new IOException("HTTP doPostForm IO Exception", e);
        }
        return result;
    }

    public static String doPostJson(String url, Object object) throws IOException {
        return doPostJson(url, object, HTTP.UTF_8);
    }

    public static String doPostJson(String url, Object object, String encoding) throws IOException {
        String result = HttpResponse.FAIL.toString();

        try (CloseableHttpClient client = HttpClients.createDefault()){
            String nurl = StringUtils.trimToNull(url);
            String sjson = JacksonUtils.toJSON(object);
            HttpPost post = new HttpPost(nurl);
            StringEntity sentity = new StringEntity(sjson, HTTP.UTF_8);
            post.setEntity(sentity);

            CloseableHttpResponse httpResponse = client.execute(post);
            HttpEntity entity = httpResponse.getEntity();
            if(Objects.nonNull(entity)){
                result = EntityUtils.toString(entity, encoding);
            }
            HttpClientUtils.closeQuietly(httpResponse);
        } catch (IOException e) {
            throw new IOException("HTTP doPostJson IO Exception", e);
        }
        return result;
    }

    protected static String newGetParams(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder(32);
        if(!MapUtils.isEmpty(params)){
            Set<Map.Entry<String, String>> keys = params.entrySet();
            Iterator<Map.Entry<String, String>> it = keys.iterator();
            boolean isfirst = true;
            Map.Entry entry;
            String key, value;
            while (it.hasNext()){
                entry = it.next();
                key = (String)entry.getKey();
                value = (String)entry.getValue();
                if(isfirst){
                    isfirst = false;
                }else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(URLEncoder.encode(value, HTTP.UTF_8));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> map = Maps.newHashMap();
        map.put("u", "947037224");
        map.put("p", "king123/*-");
        System.out.println(doPostForm("https://ssl.ptlogin2.qq.com/login",
                map));
    }
}
