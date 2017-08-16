package cooker.tool.utils.http;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yu.kequn on 2017/8/10.
 */
public class HttpResponse {
    public static final HttpResponse SUCCESS = new HttpResponse(1, "success");
    public static final HttpResponse FAIL = new HttpResponse(-1, "fail");
    public static final HttpResponse IO_ERROR = new HttpResponse(-500, "Network IO Exception");
    public static final HttpResponse TIME_OUT = new HttpResponse(-1000, "time out");

    int state;
    String msg;

    private HttpResponse(int state, String msg){
        this.state = state;
        this.msg = msg;
    }

    public int getState() {
        return state;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "{\"state\":"+state+", \"msg\":\""
               + msg + "\"}";
    }

    public static boolean isStrictEqual(HttpResponse a, HttpResponse b){
        boolean flag = false;
        flag = isEqual(a, b);
        if(flag == true){
            if(StringUtils.equals(a.getMsg(), b.getMsg()))
                flag = true;
            else
                flag = false;
        }
        return false;
    }

    public static boolean isEqual(HttpResponse a, HttpResponse b){
        boolean flag = false;
        if(ObjectUtils.allNotNull(a, b)){
            if (a.getState() == b.getState())
                flag = true;
        }
        return flag;
    }
}
