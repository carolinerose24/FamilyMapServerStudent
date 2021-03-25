package services;

import dataaccess.AuthTokenDao;
import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.UserDao;
import model.AuthTokenModel;
import model.UserModel;
import request.LoginRequest;
import result.LoginResult;

import java.sql.Connection;

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
    Database db = new Database();
    LoginResult result;

    try{
      db.openConnection();
      Connection conn = db.getConnection();
      UserDao newUser = new UserDao(conn);
      AuthTokenDao newAuth = new AuthTokenDao(conn);

      UserModel uModel = newUser.findUserLogin(request.getUsername(), request.getPassword());

      if(uModel != null){
        AuthTokenModel aModel = new AuthTokenModel(uModel.getUsername());
        newAuth.addAuthToken(aModel);

        result = new LoginResult(aModel.getAuthToken(), uModel.getUsername(), uModel.getPersonID(), true);
      } else{ //we didn't find the user in the db
        result = new LoginResult("Request property missing or has invalid value", false);
      }
      db.closeConnection(true); //true because we aren't making any changes to the db

    } catch (DataAccessException e){ //i don't think any exceptions would be thrown here
      result = new LoginResult("Request property missing or has invalid value", false);
      try{
        db.closeConnection(false);
      } catch (DataAccessException ex){
        ex.printStackTrace();
      }
    }


    //needs to check for:
    //Request property missing or has invalid value
    //Internal server error
    //return the error object

    //or interacts with DB
    //then returns the LoginResult item

    return result;
  }
}
