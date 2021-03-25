package dataaccess;

import model.AuthTokenModel;
import model.UserModel;

import java.sql.*;

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
   * @param authToken
   */
  public void addAuthToken(AuthTokenModel authToken) throws DataAccessException{
    String sql = "INSERT INTO AuthToken (AToken, Username) VALUES (?,?);";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, authToken.getAuthToken());
      stmt.setString(2, authToken.getUsername());

      stmt.executeUpdate();
    } catch (SQLException e){
      throw new DataAccessException("error adding auth token");
    }
  }






  /**
   * Check if an auth token is valid
   * @param authToken
   * @return that auth token
   */
  public AuthTokenModel getAuthToken(AuthTokenModel authToken) throws DataAccessException{
    AuthTokenModel AuthToken;
    ResultSet rs = null;
    String sql = "SELECT * FROM AuthToken WHERE AToken = ?;";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, authToken.getAuthToken());
      rs = stmt.executeQuery();
      if (rs.next()){
        AuthToken = new AuthTokenModel(rs.getString("AToken"), rs.getString("Username"));
        return AuthToken;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding auth token");
    } finally {
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }

    return null;
  }


  public AuthTokenModel getAuthToken(String authToken) throws DataAccessException{
    AuthTokenModel AuthToken;
    ResultSet rs = null;
    String sql = "SELECT * FROM AuthToken WHERE AToken = ?;";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, authToken);
      rs = stmt.executeQuery();
      if (rs.next()){
        AuthToken = new AuthTokenModel(rs.getString("AToken"), rs.getString("Username"));
        return AuthToken;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error encountered while finding auth token");
    } finally {
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }

    return null;
  }














  /**
   * Clears the AuthToken table
   * @throws DataAccessException
   */
  public void clearAuthTokenTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sql = "DELETE FROM AuthToken";
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      throw new DataAccessException("SQL Error encountered while clearing tables");
    }
  }
}
