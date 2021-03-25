package model;

import java.util.UUID;

/**
 * Model class for Auth Token
 */
public class AuthTokenModel {

  private String authToken;
  private String username;

  /**
   * Constuctor for an Auth Token Model Item
   * @param authToken
   * @param username
   */
  public AuthTokenModel(String authToken, String username) {
    this.authToken=authToken;
    this.username=username;
  }

  public AuthTokenModel(String username){
    this.username = username;
    this.authToken = UUID.randomUUID().toString();
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

  /**
   * Equals Override Method for comparing Auth Tokens
   * @param o
   * @return true/false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {return false;}
    AuthTokenModel that=(AuthTokenModel) o;
    return authToken.equals(that.authToken) &&
            username.equals(that.username);
  }

}
