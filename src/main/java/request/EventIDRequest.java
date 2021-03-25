package request;

import model.AuthTokenModel;

/**
 * Class for Event ID requests
 */
public class EventIDRequest {

  private String eventID;
  private AuthTokenModel authtoken;

  /**
   * Constructor for Event ID Requests
   * @param eventID
   * @param authtoken
   */
  public EventIDRequest(String eventID, AuthTokenModel authtoken) {
    this.eventID=eventID;
    this.authtoken=authtoken;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }

  public AuthTokenModel getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }
}
