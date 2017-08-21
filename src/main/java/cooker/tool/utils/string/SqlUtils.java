package cooker.tool.utils.string;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.StringJoiner;

/**
 * Created by yu.kequn on 2017/8/21.
 */
public class SqlUtils {

    public static String getSelectByIdSql(List<String> columns, Class c){
        StringJoiner jCols = new StringJoiner(",");
        StringBuilder sql = new StringBuilder("SELECT ");
        if(CollectionUtils.isNotEmpty(columns))
            for (String col : columns){
                jCols.add(getSqlName(col));
            }
        if(jCols.length() == 0)
            sql.append("*");
        else
            sql.append(jCols);
        sql.append(" FROM ").append(getSqlName(c.getSimpleName()));
        sql.append(" WHERE id = ?");
        return sql.toString();
    }

    public static String getInsertSql(List<String> columns, Class c){
        StringJoiner jCols = new StringJoiner(",","(",")");
        StringJoiner jVals = new StringJoiner(",","(",")");
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(getSqlName(c.getSimpleName()));
        if(CollectionUtils.isNotEmpty(columns))
            for (String col : columns){
                jCols.add(col);
                jVals.add("?");
            }
        sql.append(jCols);
        sql.append(" VALUES").append(jVals);
        return sql.toString();
    }


    protected static String getSqlName(String str){
        StringBuilder name = new StringBuilder();
        int len = str.length();
        name.append(Character.toLowerCase(str.charAt(0)));
        for(int i=1;i<len;i++){
            if(Character.isLowerCase(str.charAt(i))){
                name.append(str.charAt(i));
            }else{
                name.append("_").append(Character.toLowerCase(str.charAt(i)));
            }
        }
        return name.toString();
    }

    public static void main(String[] args) {
        List<String> lists = Lists.newArrayList();
        lists.add("x");
        System.out.println(getInsertSql(lists, SqlUtils.class));
    }

}
