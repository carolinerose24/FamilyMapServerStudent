import com.google.gson.Gson;
import result.*;

public class Deserialize {


  //turn objects into json
  public Deserialize(){

  }

  public String Deserialize(ClearResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(EventIDResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(EventResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(FillResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(LoadResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(LoginResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(PersonIDResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(PersonResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }

  public String Deserialize(RegisterResult result){
    Gson gson = new Gson();
    String newjson = gson.toJson(result);
    return newjson;
  }


  //i don't think there are any other methods that I need here?




}
