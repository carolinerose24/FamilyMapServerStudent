package result;

import model.EventModel;

/**
 * Class for Event ID Results
 */
public class EventIDResult {

  private String associatedUsername;
  private String eventID;
  private String personID;
  private Float latitude;
  private Float longitude;
  private String country;
  private String city;
  private String eventType;
  private Integer year;

  private String message;
  private boolean success;

  /**
   * Constructor for Successful Event ID Results
   * @param associatedUsername
   * @param eventID
   * @param personID
   * @param latitude
   * @param longitude
   * @param country
   * @param city
   * @param eventType
   * @param year
   * @param success
   */
  public EventIDResult(String associatedUsername, String eventID, String personID, float latitude, float longitude, String country, String city, String eventType, int year, boolean success) {
    this.associatedUsername=associatedUsername;
    this.eventID=eventID;
    this.personID=personID;
    this.latitude=latitude;
    this.longitude=longitude;
    this.country=country;
    this.city=city;
    this.eventType=eventType;
    this.year=year;
    this.success=success;
  }

  public EventIDResult(EventModel e){
    this.associatedUsername = e.getUsername();
    this.eventID = e.getEventID();
    this.personID = e.getPersonID();
    this.latitude = e.getLatitude();
    this.longitude = e.getLongitude();
    this.country = e.getCountry();
    this.city = e.getCity();
    this.eventType = e.getEventType();
    this.year = e.getYear();
    this.success = true;
  }

  /**
   * Constructor for failed Event ID Results
   * @param message
   * @param success
   */
  public EventIDResult(String message, boolean success){
    this.message=message;
    this.success=success;
//    this.latitude = null;
//    this.longitude = null;
//    this.year = null;
  }

  public String getUsername() {
    return associatedUsername;
  }

  public void setUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
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

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
