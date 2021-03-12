package handlers;

import com.google.gson.*;

public class Serialize {

  //static methods
  //make 2 methods: to de and serialize
  public static JsonObject objToJson(Object obj){

    Gson gson = new Gson();
    String jsonStr = gson.toJson(obj);
    Object objCopy = gson.fromJson(jsonStr, Object.class);



    return null;
  }

  public static Object JsonToObject(){
    return null;
  }






}
