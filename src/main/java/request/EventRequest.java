package request;

import model.AuthTokenModel;

/**
 * Class for Event Requests
 */
public class EventRequest {

  private AuthTokenModel authToken;

  /**
   * Constructor for Event Requests
   * @param authToken
   */
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
