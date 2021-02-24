package dataaccess;

import model.AuthTokenModel;
import model.UserModel;

import java.sql.Connection;

/**
 * Auth Token Data Access class to access the Auth Token table
 */
public class AuthTokenDao {


  private final Connection conn;


  /**
   * Constructor to initialize the Connection Object
   * @param conn
   */
  public AuthTokenDao(Connection conn)
  {
    this.conn = conn;
  }


  /**
   * Take in a user and get a new auth token and add it to the table
   * @param user
   * @return that auth token
   */
  public AuthTokenModel addAuthToken(UserModel user){
    return null;
  }

  /**
   * Check if an auth token is valid
   * @param user
   * @return that auth token
   */
  public AuthTokenModel getAuthToken(UserModel user){
    return null;
  }

}
