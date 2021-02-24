package request;

import model.AuthTokenModel;

/**
 * Class for Person Requests
 */
public class PersonRequest {

  private AuthTokenModel authToken;

  /**
   * Constructor for Person Requests
   * @param authToken
   */
  public PersonRequest(AuthTokenModel authToken) {
    this.authToken=authToken;
  }

  public AuthTokenModel getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthTokenModel authToken) {
    this.authToken=authToken;
  }
}
