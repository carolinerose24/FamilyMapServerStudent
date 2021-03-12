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


  private void initializeMaleNames(){
    try{
      Gson gson = new Gson();
      Reader reader =Files.newBufferedReader(Paths.get("/json/mnames.json"));
      List<String> nameList = new Gson().fromJson(reader, new TypeToken<List<String>>() {}.getType());

      for(String name : nameList){
        firstNameMList.add(name);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public FirstNameM(){
    //call the method to initialize it
    initializeMaleNames();
  }



}
