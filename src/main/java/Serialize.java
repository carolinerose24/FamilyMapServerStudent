import com.google.gson.*;
import request.*;

import java.io.Reader;

public class Serialize {

//this class turns things from json into their objects




  public Serialize(){
    //nothing here?
  }

  public EventIDRequest SerializeEventIDRequest(Reader jsonInfo){
    EventIDRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, EventIDRequest.class);
    return req;
  }

  public EventRequest SerializeEventRequest(Reader jsonInfo){
    EventRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, EventRequest.class);
    return req;
  }

  public FillRequest SerializeFillRequest(Reader jsonInfo){
    FillRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, FillRequest.class);
    return req;
  }

  public LoadRequest SerializeLoadRequest(Reader jsonInfo){
    LoadRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, LoadRequest.class);
    return req;
  }

  public LoginRequest SerializeLoginRequest(Reader jsonInfo){
    LoginRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, LoginRequest.class);
    return req;
  }

  public PersonIDRequest SerializePersonIDRequest(Reader jsonInfo){
    PersonIDRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, PersonIDRequest.class);
    return req;
  }

  public PersonRequest SerializePersonRequest(Reader jsonInfo){
    PersonRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, PersonRequest.class);
    return req;
  }

  public RegisterRequest SerializeRegisterRequest(Reader jsonInfo){
    RegisterRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, RegisterRequest.class);
    return req;
  }


  //is there any other situations where I need to serialize something?






//
//
//  //i'm not sure how this works when you can't make it general?
//  public Object ReadJson(String jsonString){ //deserialization: json to java object
//    Gson gson = new Gson();
//    FillRequest fillObj = gson.fromJson(jsonString, FillRequest.class);
//    return fillObj;
//  }
//
//  public String WriteJson(Object obj){ //serialization: java object to json representation
//    Gson gson = new Gson();
//    String jsonString = gson.toJson(obj);
//    return jsonString;
//  }

}
