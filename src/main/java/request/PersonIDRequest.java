package request;

import model.AuthTokenModel;

/**
 * Class for Person ID Requests
 */
public class PersonIDRequest {

  private String personID;
  private AuthTokenModel authtoken;

  /**
   * Constructor for Person ID Requests
   * @param personID
   * @param authtoken
   */
  public PersonIDRequest(String personID, AuthTokenModel authtoken) {
    this.personID=personID;
    this.authtoken=authtoken;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public AuthTokenModel getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(AuthTokenModel authtoken) {
    this.authtoken=authtoken;
  }
}
