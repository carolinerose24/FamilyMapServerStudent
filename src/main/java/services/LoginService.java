package services;

import request.LoginRequest;
import result.LoginResult;

/**
 * Class for Login Service
 */
public class LoginService {


  /**
   * Function to validate a login
   * @param request a Login Request Object
   * @return Login Result
   */
  public LoginResult userLogin(LoginRequest request){


    //needs to check for:
    //Request property missing or has invalid value
    //Internal server error
    //return the error object

    //or interacts with DB
    //then returns the LoginResult item

    return null;
  }
}
