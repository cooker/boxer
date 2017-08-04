package cooker.tool.utils.reflect;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/8/1 13:53<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/8/1 13:53 ck 描述<br/>
 */
public class ReflectException extends RuntimeException{

    public ReflectException() {
        super();
    }

    public ReflectException(String message) {
        super(message);
    }

    public ReflectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectException(Throwable cause) {
        super(cause);
    }

    protected ReflectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}