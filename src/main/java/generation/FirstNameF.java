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

public class FirstNameF {

  private ArrayList<String> firstNameFList;


  public String getRandomLastName(){

    int upperbound = firstNameFList.size();
    if(upperbound == 0){
      return null; //this should never happen
    }
    Random rand = new Random();
    int index = rand.nextInt(upperbound);
    return firstNameFList.get(index);
  }


  private void initializeFemaleNames(){
    try{
      Gson gson = new Gson();
      Reader reader =Files.newBufferedReader(Paths.get("/json/fnames.json"));
      List<String> nameList = new Gson().fromJson(reader, new TypeToken<List<String>>() {}.getType());

      for(String name : nameList){
        firstNameFList.add(name);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public FirstNameF(){
    //call the method to initialize it
    initializeFemaleNames();
  }


}
