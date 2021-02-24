package dataaccess;

import model.PersonModel;
import model.UserModel;

import java.sql.Connection;

/**
 * Person Data Access class to access the Person table
 */
public class PersonDao {

  private final Connection conn;


  /**
   * Constructor to initialize the Connection Object
   * @param conn
   */
  public PersonDao(Connection conn)
  {
    this.conn = conn;
  }


  /**
   * Add a person to a specific user
   * @param user
   */
  public void addPerson(UserModel user){
  }

  /**
   * Find a person in the person table, return their data
   * @param user
   */
  public PersonModel findPerson(UserModel user){
    return null;
  }

}
