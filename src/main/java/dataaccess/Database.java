package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {
  private Connection conn;

//we don't need a constructor, because we will never initialize the conn ourselves

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

  public Connection getConnection() throws DataAccessException {
    if(conn == null){
      return openConnection();
    } else {
      return conn;
    }
  }

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

  public void clearTables() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sql = "DELETE FROM User, Person, Event, AuthToken";
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      throw new DataAccessException("SQL Error encountered while clearing tables");
    }
  }



  //this should be almost the same as the database class in the example

  //all the database functions
  //open connection
  //close connection
  //get connection
  //clear tables



}
