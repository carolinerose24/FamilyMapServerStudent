package generation;

public class Location {

  private String latitude; //do these needs to be put into floats? might have to come back to this as some point
  private String Longitude;
  private String city;
  private String country;


  public Location(String latitude, String longitude, String city, String country) {
    this.latitude=latitude;
    Longitude=longitude;
    this.city=city;
    this.country=country;
  }
}
