package cooker.tool.utils.error;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/7/31 15:56<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/7/31 15:56 ck 描述<br/>
 */
public class ExceptionUtils {
    public static final String NULL_EXCEPTION = "exception is null";

    public static String getExceptionMsg(Throwable throwable){
        String errMsg = null;
        try(StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);) {
            pw.println();
            throwable.printStackTrace(pw);
            errMsg = sw.toString();
        } catch (IOException e) {
            errMsg = NULL_EXCEPTION;
        }
        return errMsg;
    }

    @Deprecated
    public static String getExceptionMsgx(Throwable throwable){
        StringBuilder sb = new StringBuilder("\n");
        StackTraceElement[] stacks = throwable.getStackTrace();
        int len = stacks.length;
        sb.append(String.format("Exception msg：%s\n", throwable.getMessage()));
        for(StackTraceElement ste : stacks){
            sb.append("\t").append(ste.toString()).append("\n");
        }
        for (Throwable th : throwable.getSuppressed()){
            sb.append(getExceptionMsg(th));
        }
        return sb.toString();
    }
}
