package request;

import model.AuthTokenModel;

/**
 * Class for Person Requests
 */
public class PersonRequest {

  private AuthTokenModel authtoken;

  /**
   * Constructor for Person Requests
   * @param authtoken
   */
  public PersonRequest(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }

  public AuthTokenModel getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }
}
