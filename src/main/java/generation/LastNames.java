package generation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LastNames {
  private ArrayList<String> lastNameList;

  public String getRandomLastName(){

    int upperbound = lastNameList.size();
    if(upperbound == 0){
      return null; //this should never happen
    }
    Random rand = new Random();
    int index = rand.nextInt(upperbound);
    return lastNameList.get(index);
  }

  public LastNames(){
    lastNameList = new ArrayList<>();
  }

  public LastNames(ArrayList<String> names){
    lastNameList = names;
  }

}
