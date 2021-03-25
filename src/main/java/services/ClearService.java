package services;

import dataaccess.*;
import result.ClearResult;

import java.io.IOException;
import java.sql.Connection;

/**
 * Class for Clear Service
 */
public class ClearService {

  //this doesn't take in a clear request object --> because it doesn't need any additional data
  //but it should still return a result

  /**
   * Function to clear the database
   * @return Clear Result
   */
  public ClearResult clearDatabase(){
    Database db = new Database();
    ClearResult result;

    try{
      db.openConnection();
      Connection conn = db.getConnection();
      UserDao newUser = new UserDao(conn);
      PersonDao newPerson = new PersonDao(conn);
      EventDao newEvent = new EventDao(conn);
      AuthTokenDao newAuth = new AuthTokenDao(conn);

      newUser.clearUserTable();
      newPerson.clearPersonTable();
      newEvent.clearEventTable();
      newAuth.clearAuthTokenTable();

      result = new ClearResult("Clear succeeded.", true);

      db.closeConnection(true);

    } catch (DataAccessException e){
      result = new ClearResult("Error: Internal server error", false);
      try{
        db.closeConnection(false);
      } catch (DataAccessException ex){
        ex.printStackTrace();
      }
    }

    return result;
  }
}
