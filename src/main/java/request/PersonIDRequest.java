package request;

import model.AuthTokenModel;

/**
 * Class for Person ID Requests
 */
public class PersonIDRequest {

  private String personID;
  private String authtoken;

  /**
   * Constructor for Person ID Requests
   * @param personID
   * @param authtoken
   */
  public PersonIDRequest(String personID, String authtoken) {
    this.personID=personID;
    this.authtoken=authtoken;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }
}
