package dataaccess;

import model.PersonModel;
import model.UserModel;

import java.sql.*;

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
   * @param person
   */
  public void addPerson(PersonModel person) throws DataAccessException {

    String sql = "INSERT INTO Person (PersonID, Username, FirstName, LastName, Gender, FatherID, MotherID, SpouseID) VALUES (?,?,?,?,?,?,?,?);";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, person.getPersonID());
      stmt.setString(2, person.getUsername());
      stmt.setString(3, person.getFirstName());
      stmt.setString(4, person.getLastName());
      stmt.setString(5, person.getGender());
      stmt.setString(6, person.getFatherID());
      stmt.setString(7, person.getMotherID());
      stmt.setString(8, person.getSpouseID());

      stmt.executeUpdate();
    } catch (SQLException e){
      throw new DataAccessException("Error adding a person");
    }

  }

  /**
   * Find a person in the person table, return their data
   * @param person
   */
  public PersonModel findPerson(PersonModel person) throws DataAccessException{
    if(person == null){
      return null;
    }
    PersonModel Person;
    ResultSet rs = null;
    String sql = "SELECT * FROM Person WHERE PersonID = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, person.getPersonID());
      rs = stmt.executeQuery();
      if(rs.next()){

        Person = new PersonModel(rs.getString("PersonID"), rs.getString("Username"),
                rs.getString("FirstName"), rs.getString("LastName"),
                rs.getString("Gender"),rs.getString("FatherID"),
                rs.getString("MotherID"), rs.getString("SpouseID"));
        return Person;
      }
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error finding a person");
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
   * Clears the Person table
   * @throws DataAccessException
   */
  public void clearPersonTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sql = "DELETE FROM Person";
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      throw new DataAccessException("SQL Error encountered while clearing tables");
    }
  }

  /**
   * TAKE A USERNAME AND RETURN all the people associated with that uSER
   * @param Username
   * @return
   */
  public UserModel getUsernameInfo (String Username){ //or should this be a usermodle object?

    return null;
  }

  /**
   * take in a user and then delete ALL THE PEOPLE ASSOCIATED WITH THAT USERNAME
   * @param user
   */
  public void deleteUsernameData(UserModel user){

  }
}
