package handlers;

import com.google.gson.*;
import request.FillRequest;

public class Serialize {

  //static methods
  //make 2 methods: to de and serialize
//  public static JsonObject objToJson(Object obj){
//
//    Gson gson = new Gson();
//    String jsonStr = gson.toJson(obj);
//    Object objCopy = gson.fromJson(jsonStr, Object.class);
//
//
//
//    return null;
//  }
//
//  public static Object JsonToObject(){
//    return null;
//  }
//
//
//
//  public static void TOJSON(){
//    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//    String jsonStr = gson.toJson(); //put the json thing in here
//  }




  //i'm not sure how this works when you can't make it general?
  public Object ReadJson(String jsonString){ //deserialization: json to java object
    Gson gson = new Gson();
    FillRequest fillObj = gson.fromJson(jsonString, FillRequest.class);
    return fillObj;
  }

  public String WriteJson(Object obj){ //serialization: java object to json representation
    Gson gson = new Gson();
    String jsonString = gson.toJson(obj);
    return jsonString;
  }






}
