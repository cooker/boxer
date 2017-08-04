package cooker.tool.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/7/31 15:56<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/7/31 15:56 ck 描述<br/>
 */
class PropertiesUtils {
    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private PropertiesUtils(){super();}

    /**
     * @param filename 文件名
     * @param mode 配置文件读取方式（IN_JAR，OUTER_JAR）
     * @return
     */
    public static Properties getProperties(String filename, LOAD_MODE mode) throws PropertiesException {
        Properties properties = null;
        switch (mode){
            case IN_JAR:
                properties = getInJarProperties(filename);
            break;
            case OUTER_JAR:
                properties = getOuterJarProperties(filename);
                break;
            default: break;
        }
        return properties;
    }

    /**
     * 获取包内配置文件
     * @param filename
     * @return
     */
    public static Properties getInJarProperties(String filename) throws PropertiesException {
        Properties properties = null;
        ClassLoader cl = Thread.currentThread().getClass().getClassLoader();
        try(InputStream in = cl.getResourceAsStream(filename)){
            properties = new Properties();
            properties.load(in);
        }catch (Exception e){
            throw new PropertiesException("包内配置文件加载失败！", e);
        }
        return properties;
    }

    /**
     * 获取包外配置文件
     * @param filename
     * @return
     */
    public static Properties getOuterJarProperties(String filename) throws PropertiesException {
        Properties properties = null;
        try (FileInputStream in = new FileInputStream(new File(filename))){
            properties = new Properties();
            properties.load(in);
        }catch (Exception e){
            throw new PropertiesException("包外配置文件加载失败！", e);
        }
        return properties;
    }

    public static enum LOAD_MODE{
        IN_JAR, OUTER_JAR;
    }
}