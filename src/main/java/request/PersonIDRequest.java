package request;

import model.AuthTokenModel;

/**
 * Class for Person ID Requests
 */
public class PersonIDRequest {

  private String personID;
  private AuthTokenModel authToken;

  /**
   * Constructor for Person ID Requests
   * @param personID
   * @param authToken
   */
  public PersonIDRequest(String personID, AuthTokenModel authToken) {
    this.personID=personID;
    this.authToken=authToken;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public AuthTokenModel getAuthToken() {
    return authToken;
  }

  public void setAuthToken(AuthTokenModel authToken) {
    this.authToken=authToken;
  }
}
