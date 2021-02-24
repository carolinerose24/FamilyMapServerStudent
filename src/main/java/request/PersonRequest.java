package request;

import model.AuthTokenModel;

public class PersonRequest {

  private AuthTokenModel authToken;

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
