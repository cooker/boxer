package cooker.tool.utils.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by ck on 2017/8/1.
 * TODO
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Reflect {
    /**
     * 是否忽略不存在的属性
     */
    public boolean ignoreUnknown() default false;
}
