package yuki.resource.extended;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by Akeno on 2017/06/30.
 */

public class GsonConvert {
    public static String SerializeObject(Object object){
        Gson gson=new Gson();
        return gson.toJson(object);
    }

    public static <T> T DeserializeObject(String gson,Class<T> TClass){
        Gson g=new Gson();
        return (T)g.fromJson(gson, TClass);
    }

}
