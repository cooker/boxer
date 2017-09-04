package cooker.tool.utils.weixin.sugar;

/**
 * Created by yu.kequn on 2017/9/1.
 */
public class PostMsg {
    //Key
    private String sckey;
    //消息标题，最长256
    private String text;
    //消息内容，最长64KB
    private String desp;

    public PostMsg(){}

    public PostMsg(String sckey, String text, String desp) {
        this.sckey = sckey;
        this.text = text;
        this.desp = desp;
    }

    public String getSckey() {
        return sckey;
    }

    public void setSckey(String sckey) {
        this.sckey = sckey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

}
