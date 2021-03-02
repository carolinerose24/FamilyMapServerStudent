package dataaccess;

import model.AuthTokenModel;
import model.PersonModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * User Data Access class to access the User table
 */
public class UserDao {

  //methods to operate on the DB
  //inputs and outputs are the model objects/classes

  private final Connection conn;


  /**
   * Constructor to initialize the Connection Object
   * @param conn
   */
  public UserDao(Connection conn)
  {
    this.conn = conn;
  }

  /**
   * Takes in the user information and adds them to the user, person, and auth tables and gives back
   * the new authToken that was made
   * @param user
   * @return AuthTokenModel
   * @throws DataAccessException
   */
  public AuthTokenModel addUser(UserModel user) throws DataAccessException{
    return null;
  }

  /**
   * Checks the username and password, returns info about that user and a new auth token
   * needs to also get the personID
   * @param user
   * @return AuthTokenModel
   * @throws DataAccessException
   */
  public AuthTokenModel findUserLogin(UserModel user) throws DataAccessException{
    return null;
  }


  /**
   * Clears the User table
   * @throws DataAccessException
   */
  public void clearUserTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sql = "DELETE FROM User";
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      throw new DataAccessException("SQL Error encountered while clearing tables");
    }
  }


}
