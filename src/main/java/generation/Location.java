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

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude=latitude;
  }

  public String getLongitude() {
    return Longitude;
  }

  public void setLongitude(String longitude) {
    Longitude=longitude;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city=city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country=country;
  }
}
