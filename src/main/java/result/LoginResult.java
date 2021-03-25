package result;

/**
 * Class for Login Results
 */
public class LoginResult {

  private String authtoken;
  private String username;
  private String personID;

  private String message;
  private boolean success;

  /**
   * Constructor for Successful Login Results
   * @param authtoken
   * @param username
   * @param personID
   * @param success
   */
  public LoginResult(String authtoken, String username, String personID, boolean success) {
    this.authtoken=authtoken;
    this.username=username;
    this.personID=personID;
    this.success=success;
  }

  /**
   * Constructor for failed Login Results
   * @param message
   * @param success
   */
  public LoginResult(String message, boolean success){
    this.message=message;
    this.success=success;
  }

  public String getAuthtoken() {
    return authtoken;
  }

  public void setAuthtoken(String authtoken) {
    this.authtoken=authtoken;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
