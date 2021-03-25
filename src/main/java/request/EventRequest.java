package request;

import model.AuthTokenModel;

/**
 * Class for Event Requests
 */
public class EventRequest {

  private AuthTokenModel authtoken;

  /**
   * Constructor for Event Requests
   * @param authtoken
   */
  public EventRequest(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }

  public AuthTokenModel getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }

}
