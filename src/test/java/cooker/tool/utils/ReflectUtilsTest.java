package cooker.tool.utils;

import cooker.tool.utils.reflect.IReflectMethod;
import cooker.tool.utils.reflect.ReflectAttr;
import cooker.tool.utils.reflect.ReflectUtils;
import org.junit.Test;

/**
 * Created by yu.kequn on 2017/8/1.
 */
public class ReflectUtilsTest {

    @Test
    public void invokeTest(){
        ReflectUtilsTest r = new ReflectUtilsTest();
        ReflectUtils utils = new ReflectUtils();
        IReflectMethod method = new ReflectAttr();
        method.setMethodName("print");
        method.addParam("name","ssss",String.class);
        utils.inokeMethod(r,method);
    }

    protected void print(){
        System.out.println("---------------------------");
    }
}
