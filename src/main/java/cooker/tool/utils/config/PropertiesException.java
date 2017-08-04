package cooker.tool.utils.config;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/7/31 15:56<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/7/31 15:56 ck 描述<br/>
 */
public class PropertiesException extends Exception {
    public PropertiesException() {
        super();
    }

    public PropertiesException(String message) {
        super(message);
    }

    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertiesException(Throwable cause) {
        super(cause);
    }

    public PropertiesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
