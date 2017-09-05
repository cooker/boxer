package cooker.tool.utils.reflect;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/8/1 10:55<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/8/1 10:55 ck 描述<br/>
 */
public class ReflectUtils {

    public <T> void fillAttrs(Class<T> refc, Object refo, IReflectField rfield){
        int len = rfield.getAttrsLength();
        for(int i = 0;i < len; i++){
            try {
                FieldUtils.writeField(refo, rfield.getAttrName(i),rfield.getAttrValue(i), true);
            }catch (IllegalAccessException e){
                //ingore
            }
        }
    }

    public <T> T getAttr(Class<T> refc, Object refo, String name){
        Field field = FieldUtils.getField(refc, name, true);
        Object robj = null;
        try {
            robj = field.get(refo);
        } catch (IllegalAccessException e) {
            //ingore
        }
        return (T)robj;
    }

    public <T> IReflectField getAttrs(Class<T> refc, Object refo){
        IReflectField field = new ReflectAttr();
        Field[] fields = FieldUtils.getAllFields(refc);
        for(Field f : fields){
            f.setAccessible(true);
            try {
                field.addAttr(f.getName(),f.get(refo),f.getType());
            } catch (IllegalAccessException e) {
                //ingore
            }
        }
        return field;
    }

    public <T> Map<String, Object> getMapAttrs(Class<T> refc, Object refo){
        IReflectField field = getAttrs(refc, refo);
        Map<String, Object> rmap = Maps.newHashMap();
        int len = field.getAttrsLength();
        for(int i = 0; i < len; i++){
            rmap.put(field.getAttrName(i), field.getAttrValue(i));
        }
        return rmap;
    }

    /**
     *
     * @param refc 目标类
     * @param annc 需要或取得的注解
     * @return
     */
    public <T> T getClassAnnotation(Class refc, Class<T> annc){
        for (Annotation ann : refc.getAnnotations()){
            if(StringUtils.equals(ann.annotationType().getName(), annc.getName()))
                return (T)ann;
        }
        return null;
    }

    public <T> T inokeMethod(Object refo, IReflectMethod method){
        Object robj = null;
        int len = method.getParamsLength();
        Object[] params = new Object[len];
        Class[] paramTypes = new Class[len];
        for(int i = 0; i < len; i++){
            params[i] = method.getParamValue(i);
            paramTypes[i] = method.getParamClass(i);
        }
        try {
            robj = MethodUtils.invokeMethod(refo, true, method.getMethodName(),
                    params, paramTypes);
        } catch (Exception e) {
            throw new ReflectException(String.format("调用[%s]方法异常：",method.getMethodName()), e);
        }
        return (T)robj;
    }

    public <T> T inokeStaticMethod(Class refc, IReflectMethod method){
        Object robj = null;
        int len = method.getParamsLength();
        Object[] params = new Object[len];
        Class[] paramTypes = new Class[len];
        for(int i = 0; i < len; i++){
            params[i] = method.getParamValue(i);
            paramTypes[i] = method.getParamClass(i);
        }
        try {
            robj = MethodUtils.invokeStaticMethod(refc, method.getMethodName(),
                    params, paramTypes);
        } catch (Exception e) {
            throw new ReflectException(String.format("调用[%s]方法异常：",method.getMethodName()), e);
        }
        return (T)robj;
    }

}