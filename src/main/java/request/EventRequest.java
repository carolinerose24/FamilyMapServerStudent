package request;

import model.AuthTokenModel;

public class EventRequest {

  private AuthTokenModel authToken;

  public EventRequest(AuthTokenModel authToken) {
    this.authToken=authToken;
  }

  public AuthTokenModel getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthTokenModel authToken) {
    this.authToken=authToken;
  }

}
