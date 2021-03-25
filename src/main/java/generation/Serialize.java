package generation;

import com.google.gson.*;
import request.*;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;

public class Serialize {

//this class turns things from json into their objects




  public Serialize(){
    //nothing here?
  }

  public static EventIDRequest SerializeEventIDRequest(Reader jsonInfo){
    EventIDRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, EventIDRequest.class);
    return req;
  }

  public static EventRequest SerializeEventRequest(Reader jsonInfo){
    EventRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, EventRequest.class);
    return req;
  }

  public static FillRequest SerializeFillRequest(Reader jsonInfo){
    FillRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, FillRequest.class);
    return req;
  }

  public static LoadRequest SerializeLoadRequest(Reader jsonInfo){
    LoadRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, LoadRequest.class);
    return req;
  }

  public static LoginRequest SerializeLoginRequest(Reader jsonInfo){
    LoginRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, LoginRequest.class);
    return req;
  }

  public static PersonIDRequest SerializePersonIDRequest(Reader jsonInfo){
    PersonIDRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, PersonIDRequest.class);
    return req;
  }

  public static PersonRequest SerializePersonRequest(Reader jsonInfo){
    PersonRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, PersonRequest.class);
    return req;
  }

  public static RegisterRequest SerializeRegisterRequest(Reader jsonInfo){
    RegisterRequest req;
    Gson gson = new Gson();
    req = gson.fromJson(jsonInfo, RegisterRequest.class);
    return req;
  }


  //should also have code here to reads in the name and location files into objects

  public static LocationData serializeLocationList(String filename){
    Gson gson = new Gson();
    try{
      LocationData locations = gson.fromJson(new FileReader(filename), LocationData.class);
      return locations;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }


  public static ArrayList<String> serializeNameList(String filename){
    ArrayList<String> nameList = new ArrayList<>();
      Object obj =null;
      try {
        obj=new JsonParser().parse(new FileReader(filename));
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      JsonObject jo = (JsonObject) obj;
      JsonArray ja = (JsonArray) jo.get("data");
      for(int i = 0; i < ja.size(); i++){
        String name = ja.get(i).toString();
        name = name.replaceAll("\"", "");
        nameList.add(name);
      }
    return nameList;
  }


  public static FirstNameF serializeFemaleNameList(String filename)
  {
    Gson gson = new Gson();
    try{
      FirstNameF femaleNames = gson.fromJson(new FileReader(filename), FirstNameF.class);
      return femaleNames;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }

  public static FirstNameM serializeMaleNameList(String filename)
  {
    Gson gson = new Gson();
    try{
      FirstNameM maleNames = gson.fromJson(new FileReader(filename), FirstNameM.class);
      return maleNames;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }

  public static LastNames serializeLastNameList(String filename)
  {
    Gson gson = new Gson();
    try{
      LastNames lastNameList = gson.fromJson(new FileReader(filename), LastNames.class);
      return lastNameList;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }



}
