import com.google.gson.*;
import generation.FirstNameF;
import generation.FirstNameM;
import generation.LastNames;
import generation.LocationData;
import request.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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


  //should also have code here to reads in the name and location files into objects

  public LocationData serializeLocationList(String filename){
    Gson gson = new Gson();
    try{
      LocationData locations = gson.fromJson(new FileReader(filename), LocationData.class);
      return locations;
    } catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return null;
  }


  public FirstNameF serializeFemaleNameList(String filename)
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

  public FirstNameM serializeMaleNameList(String filename)
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

  public LastNames serializeLastNameList(String filename)
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
