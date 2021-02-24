package request;

/**
 * Class for Login Requests
 */
public class LoginRequest {

  private String username;
  private String password;

  /**
   * Constructor for Login Requests
   * @param username
   * @param password
   */
  public LoginRequest(String username, String password) {
    this.username=username;
    this.password=password;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }
}
