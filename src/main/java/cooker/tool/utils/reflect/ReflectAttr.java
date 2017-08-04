package cooker.tool.utils.reflect;

import com.google.common.collect.Lists;
import java.util.List;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/8/1 11:08<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/8/1 11:08 ck 描述<br/>
 */
public class ReflectAttr implements IReflectField, IReflectMethod {
    /**
     * 对象属性设置
     */
    private List<String> strAttrs = Lists.newArrayList();
    private List<Object> objAttrs = Lists.newArrayList();
    private List<Class> classAttrs = Lists.newArrayList();

    /**
     * 对象方法设置
     */
    private String methodName = "";
    private List<String> nameParams = Lists.newArrayList();
    private List<Object> objParams = Lists.newArrayList();
    private List<Class> classParams = Lists.newArrayList();
    private Class returnType = Void.class;

    @Override
    public int getAttrsLength() {
        return strAttrs.size();
    }

    @Override
    public void clear(){
        strAttrs.clear();
        objAttrs.clear();
        classAttrs.clear();

        methodName = "";
        nameParams.clear();
        objParams.clear();
        classParams.clear();
        returnType = Void.class;
    }

    @Override
    public String getAttrName(int index) {
        return strAttrs.get(index);
    }

    @Override
    public Object getAttrValue(int index) {
        return objAttrs.get(index);
    }

    @Override
    public Class getAttrClass(int index) {
        return classAttrs.get(index);
    }

    @Override
    public void addAttr(String name, Object value, Class aclass) {
        strAttrs.add(name);
        objAttrs.add(value);
        classAttrs.add(aclass);
    }

    @Override
    public void addAttrs(String[] names, Object[] values, Class[] aclasss){
        strAttrs.addAll(Lists.newArrayList(names));
        objAttrs.addAll(Lists.newArrayList(values));
        classAttrs.addAll(Lists.newArrayList(aclasss));
    }

    @Override
    public int getParamsLength() {
        return nameParams.size();
    }

    @Override
    public String getMethodName() {
        return methodName;
    }

    @Override
    public Class getReturnType() {
        return returnType;
    }

    @Override
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void setReturnType(Class returnType) {
        if (returnType == null)
            this.returnType = Void.class;
        else
            this.returnType = returnType;
    }

    @Override
    public String getParamName(int index) {
        return nameParams.get(index);
    }

    @Override
    public Object getParamValue(int index) {
        return objParams.get(index);
    }

    @Override
    public Class getParamClass(int index) {
        return classParams.get(index);
    }

    @Override
    public void addParam(String name, Object value, Class aclass) {
        nameParams.add(name);
        objParams.add(value);
        classParams.add(aclass);
    }

    @Override
    public void addParams(String[] names, Object[] values, Class[] aclasss) {
        nameParams.addAll(Lists.newArrayList(names));
        objParams.addAll(Lists.newArrayList(values));
        classParams.addAll(Lists.newArrayList(aclasss));
    }
}