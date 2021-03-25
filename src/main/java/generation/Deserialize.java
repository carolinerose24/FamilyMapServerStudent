package generation;

import com.google.gson.Gson;
import result.*;

public class Deserialize {


  //turn objects into json
  public Deserialize(){

  }

  public static String toJsonDeserialize(ClearResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(EventIDResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(EventResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(FillResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(LoadResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(LoginResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(PersonIDResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(PersonResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }

  public static String toJsonDeserialize(RegisterResult result){
    Gson gson = new Gson();
    return gson.toJson(result);
  }


  //i don't think there are any other methods that I need here?




}
