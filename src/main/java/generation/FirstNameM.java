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

public class FirstNameM {

  private ArrayList<String> firstNameMList;

  public String getRandomLastName(){

    int upperbound = firstNameMList.size();
    if(upperbound == 0){
      return null; //this should never happen
    }
    Random rand = new Random();
    int index = rand.nextInt(upperbound);
    return firstNameMList.get(index);
  }

  public FirstNameM(){
    firstNameMList = new ArrayList<>();
  }

  public FirstNameM(ArrayList<String> names){
    firstNameMList = names;
  }

}
