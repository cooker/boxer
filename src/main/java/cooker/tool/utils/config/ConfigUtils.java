package cooker.tool.utils.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Properties;

/**
 * 创建者:   ck<br/>
 * 创建时间: 2017/7/31 15:56<br/>
 * 功能描述:<br/>
 * 修改历史:<br/>
 * 2017/7/31 15:56 ck 描述<br/>
 */
public class ConfigUtils {

    public static Config getConfig(String filename, PropertiesUtils.LOAD_MODE mode)
            throws PropertiesException {
        Config conf = null;
        Properties pro = PropertiesUtils.getProperties(filename, mode);
        conf = new Config(pro);
        return conf;
    }

    protected static class Config{
        Properties pro;
        protected Config(Properties pro){
            this.pro = pro;
        }

        public Integer getInteger(String key){
            return getInteger(key, 0);
        }

        public Integer getInteger(String key, int defVal){
            String sval = getString(key);
            return new Integer(NumberUtils.toInt(sval, defVal));
        }

        public Long getLong(String key){
            return getLong(key,0L);
        }

        public Long getLong(String key, Long defVal){
            String sval = getString(key);
            return new Long(NumberUtils.toLong(sval, defVal));
        }

        public Boolean getBoolean(String key){
            return getBoolean(key,false);
        }

        public Boolean getBoolean(String key, Boolean defVal){
            String sval = getString(key);
            if(StringUtils.equalsIgnoreCase(sval, "true"))
                return true;
            else if(getLong(key)>0)
                return true;
            return false;
        }

        public String getString(String key){
            return getString(key, "");
        }

        public String getString(String key, String defVal){
            return pro.getProperty(key, defVal);
        }
    }
}
