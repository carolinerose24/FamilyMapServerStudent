package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
  private Connection conn;

//we don't need a constructor, because we will never initialize the conn ourselves

  /**
   * Method to open a connection
   * @return that connection
   * @throws DataAccessException
   */
  public Connection openConnection() throws DataAccessException {
    try{
      final String CONNECTION_URL = "jdbc:sqlite:familymap.sqlite";
      conn = DriverManager.getConnection(CONNECTION_URL);
      conn.setAutoCommit(false);
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Unable to open connection to database");
    }
    return conn;
  }

  /**
   * Method to retrieve the connection object
   * @return connection
   * @throws DataAccessException
   */
  public Connection getConnection() throws DataAccessException {
    if(conn == null){
      return openConnection();
    } else {
      return conn;
    }
  }

  /**
   * Method to close the connection
   * @param commit boolean to tell the method to commit or rollback changes
   * @throws DataAccessException
   */
  public void closeConnection(boolean commit) throws DataAccessException {
    try{
      if(commit){
        conn.commit();
      } else {
        conn.rollback();
      }
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Unable to close database connection");
    }
  }

//  /**
////   * Method to clear All the tables (i don't think i need this here, it is specific to all the DAO classes?
////   * @throws DataAccessException
////   */
////  public void clearTables() throws DataAccessException {
////    try (Statement stmt = conn.createStatement()){
////      String sql = "DELETE FROM User, Person, Event, AuthToken";
////      stmt.executeUpdate(sql);
////    } catch (SQLException e){
////      throw new DataAccessException("SQL Error encountered while clearing tables");
////    }
////  }

}
