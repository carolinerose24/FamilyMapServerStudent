package model;

/**
 * Model class for Event Objects
 */
public class EventModel {

  private String eventID;
  private String username;
  private String personID;
  private float latitude;
  private float longitude;
  private String country;
  private String city;
  private String eventType;
  private int year;

  /**
   * Constructor for Event Objects
   * @param eventID
   * @param username
   * @param personID
   * @param latitude
   * @param longitude
   * @param country
   * @param city
   * @param eventType
   * @param year
   */
  public EventModel(String eventID, String username, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
    this.eventID=eventID;
    this.username=username;
    this.personID=personID;
    this.latitude=latitude;
    this.longitude=longitude;
    this.country=country;
    this.city=city;
    this.eventType=eventType;
    this.year=year;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

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

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country=country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city=city;
  }

  public String getEventType() {
    return eventType;
  }

  public void setEventType(String eventType) {
    this.eventType=eventType;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year=year;
  }


  /**
   * Equals Override Method for comparing Events
   * @param o
   * @return true/false
   */
  @Override
  public boolean equals(Object o) {
    if (o == null)
      return false;
    if (o instanceof EventModel) {
      EventModel oEvent = (EventModel) o;
      return oEvent.getEventID().equals(getEventID()) &&
              oEvent.getUsername().equals(getUsername()) &&
              oEvent.getPersonID().equals(getPersonID()) &&
              oEvent.getLatitude() == (getLatitude()) &&
              oEvent.getLongitude() == (getLongitude()) &&
              oEvent.getCountry().equals(getCountry()) &&
              oEvent.getCity().equals(getCity()) &&
              oEvent.getEventType().equals(getEventType()) &&
              oEvent.getYear() == (getYear());
    } else {
      return false;
    }
  }
}
