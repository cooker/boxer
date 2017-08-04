package cooker.tool.utils.reflect;

/**
 * Created by yu.kequn on 2017/8/1.
 */
public interface IReflectMethod {
    int getParamsLength();

    void clear();

    String getMethodName();

    Class getReturnType();

    void setMethodName(String methodName);

    void setReturnType(Class returnType);

    String getParamName(int index);

    Object getParamValue(int index);

    Class  getParamClass(int index);

    void addParam(String name, Object value, Class aclass);

    void addParams(String[] names, Object[] values, Class[] aclasss);

}
