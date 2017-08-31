package cooker.tool.utils.security;

/**
 * Created by yu.kequn on 2017/8/30.
 */
public class ExecutionUtils {
    /**
     * 获取执行时间（ms）
     */
    public static long getExecMs(IExecutionTimeFun ifun){
        long t1 = System.currentTimeMillis(), t2;
        ifun.exec();
        t2 = System.currentTimeMillis();
        return t2 - t1;
    }
}
