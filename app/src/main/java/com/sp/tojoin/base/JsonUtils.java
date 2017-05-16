package com.sp.tojoin.base;

/**
 * Created by Administrator on 2017/5/16.
 */

public class JsonUtils {

    StringBuffer jsonstr=new StringBuffer("");

    private int count=0;

    public JsonUtils(){
        jsonstr.append("{");
    }

    public static JsonUtils Builder(){
        return new JsonUtils();
    }

    public JsonUtils addItem(String key,String value){
        if (count!=0){
            jsonstr.append(",");
        }
        jsonstr.append("\""+key+"\":\""+value+"\"");
        count++;
        return this;
    }

    public JsonUtils addItem(String key,int value){
        if (count!=0){
            jsonstr.append(",");
        }
        jsonstr.append("\""+key+"\":"+value);
        count++;
        return this;
    }

    public String build(){
        jsonstr.append("}");
        return jsonstr.toString();
    }


}
