package generation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


public class LocationData {

  private ArrayList<Location> data;


  private void initializeLocations(){
    try{
      Gson gson = new Gson();
      Reader reader =Files.newBufferedReader(Paths.get("/json/locations.json"));
      List<Location> locations = new Gson().fromJson(reader, new TypeToken<List<Location>>() {}.getType());

      for(Location loc : locations){
        data.add(loc);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Location getRandomLocation(){

    int upperbound = data.size();
    if(upperbound == 0){
      return null; //this should never happen
    }
    Random rand = new Random();
    int index = rand.nextInt(data.size());
    return data.get(index);
  }


  public LocationData(){
    data = new ArrayList<>();
    initializeLocations();
  }

}
