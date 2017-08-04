package cooker.tool.utils.reflect;

/**
 * Created by yu.kequn on 2017/8/1.
 */
public interface IReflectField {

    int getAttrsLength();

    void clear();

    String getAttrName(int index);

    Object getAttrValue(int index);

    Class getAttrClass(int index);

    void addAttr(String name, Object value, Class aclass);

    void addAttrs(String[] names, Object[] values, Class[] aclasss);
}
