package request;

import model.AuthTokenModel;

public class EventIDRequest {

  private String eventID;
  private AuthTokenModel authToken;

  public EventIDRequest(String eventID, AuthTokenModel authToken) {
    this.eventID=eventID;
    this.authToken=authToken;
  }

  public String getEventID() {
    return eventID;
  }

  public void setEventID(String eventID) {
    this.eventID=eventID;
  }

  public AuthTokenModel getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthTokenModel authToken) {
    this.authToken=authToken;
  }
}
