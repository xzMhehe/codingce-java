package cn.com.codingce.hive;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.util.HashMap;

public class Udf extends UDF {

    private static HashMap<String,String> countryMap = new HashMap();

    static {
        countryMap.put("tianjin", "天津");
        countryMap.put("hebei", "河北");
        countryMap.put("shandong", "山东");

    }

    //此段代码进行国家的转换
    public  String evaluate(String nation){
        String country  = countryMap.get(nation);
        if(country ==null){
            return "其他";
        }else{
            return country;
        }
    }

    public  String evaluate(String nation,String ip){

        return nation;
    }
}
