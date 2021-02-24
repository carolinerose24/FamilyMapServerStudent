package result;

public class LoginResult {

  private String authToken;
  private String username;
  private String personID;

  private String message;
  private boolean success;

  public LoginResult(String authToken, String username, String personID, boolean success) {
    this.authToken=authToken;
    this.username=username;
    this.personID=personID;
    this.success=success;
  }

  public LoginResult(String message, boolean success){
    this.message=message;
    this.success=success;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken=authToken;
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
