package generation;

public class Location {

  private float latitude; //do these needs to be put into floats? might have to come back to this as some point
  private float longitude;
  private String city;
  private String country;


  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude=latitude;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude=longitude;
  }

  public Location(float latitude, float longitude, String city, String country) {
    this.latitude=latitude;
    this.longitude=longitude;
    this.city=city;
    this.country=country;
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
