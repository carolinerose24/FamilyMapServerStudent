package generation;

import java.util.ArrayList;
import java.util.Random;

public class NameData {

  private ArrayList<String> nameList;

  public ArrayList<String> getNameList() {
    return nameList;
  }

  public void setNameList(ArrayList<String> nameList) {
    this.nameList=nameList;
  }

  public NameData(){
    nameList = new ArrayList<>();
  }

  public NameData(ArrayList<String> nameList){
    this.nameList = nameList;
  }

  public String getNameAt(int index){
    return nameList.get(index);
  }

  public String getRandomName(){
    int upperbound = nameList.size();
    if(upperbound == 0){
      System.out.println("PROBLEM IN GET RANDOM NAME");
      return null; //this should never happen
    }
    Random rand = new Random();
    int index = rand.nextInt(upperbound);
//    return "thisname";
    return nameList.get(index);
  }

}
