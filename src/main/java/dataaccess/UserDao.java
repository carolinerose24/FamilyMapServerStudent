package dataaccess;

import model.AuthTokenModel;
import model.PersonModel;
import model.UserModel;

import java.sql.*;

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
   * Takes in the user information and adds them to the user table
   * @param user
   * @throws DataAccessException
   */
  public void addUser(UserModel user) throws DataAccessException{

    //also need to check that all of the fields have SOMETHING in them
    //also username needs to be unique, so we need to throw an error if it isn't correct
      //will it do this automatically? if the username is the local key for the user table?

    String sql = "INSERT INTO User (Username, PersonID, Password, Email, FirstName, LastName, Gender) VALUES (?,?,?,?,?,?,?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){

      if(user.getUsername().isEmpty() || user.getPersonID().isEmpty() || user.getPassword().isEmpty() ||
              user.getEmail().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getGender().isEmpty()){
        throw new DataAccessException("error: empty field in userDAO");
      }
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPersonID());
      stmt.setString(3, user.getPassword());
      stmt.setString(4, user.getEmail());
      stmt.setString(5, user.getFirstName());
      stmt.setString(6, user.getLastName());
      if(user.getGender().length() != 1 || ((!user.getGender().equals("f")) && (!user.getGender().equals("f")))){
        throw new DataAccessException("error: invalid gender in UserDao");
      }
      stmt.setString(7, user.getGender());

      stmt.executeUpdate();
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error inserting a user");
    }
  }

  /**
   * Checks the username and password, returns info about that user
   * needs to also get the personID
   * @param user
   * @return AuthTokenModel
   * @throws DataAccessException
   */
  public UserModel findUserLogin(UserModel user) throws DataAccessException{

    UserModel User;
    ResultSet rs = null;
    String sql = "SELECT * FROM User WHERE Username = ? AND Password = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, user.getUsername());
      stmt.setString(2, user.getPassword());
      rs = stmt.executeQuery();
      if(rs.next()){
        User = new UserModel(rs.getString("Username"), rs.getString("PersonID"),
                rs.getString("Password"), rs.getString("Email"),
                rs.getString("FirstName"),rs.getString("LastName"),
                rs.getString("Gender"));
        return User;
      }
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error when finding user");
    } finally {
      if(rs != null){
        try{
          rs.close();
        } catch (SQLException e){
          e.printStackTrace();
        }
      }
    }
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
