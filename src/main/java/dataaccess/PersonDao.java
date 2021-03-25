package dataaccess;

import generation.NameData;
import generation.Serialize;
import model.PersonModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Person Data Access class to access the Person table
 */
public class PersonDao {

  private final Connection conn;
  private NameData femaleNames;
  private NameData maleNames;
  private NameData lastNames;

  /**
   * Constructor to initialize the Connection Object and names lists
   * @param conn
   */
  public PersonDao(Connection conn)
  {
    this.conn = conn;
    femaleNames = new NameData(Serialize.serializeNameList("json/fnames.json"));
    maleNames = new NameData(Serialize.serializeNameList("json/mnames.json"));
    lastNames = new NameData(Serialize.serializeNameList("json/snames.json"));
//    ArrayList<String> temp = Serialize.serializeNameList("json/fnames.json");
//    femaleNames.setNameList(temp);
//    femaleNames.setNameList(Serialize.serializeNameList("json/fnames.json"));// = Serialize.serializeNameList("json/fnames.json");
//    maleNames.setNameList(Serialize.serializeNameList("json/mnames.json"));// = Serialize.serializeNameList("json/mnames.json");
//    lastNames.setNameList(Serialize.serializeNameList("json/snames.json"));// = Serialize.serializeNameList("json/snames.json");
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
      if(person.getGender().length() != 1 || ((!person.getGender().equals("f")) && (!person.getGender().equals("m")))) {
        throw new DataAccessException("error: invalid gender in Persondao");
      }
      stmt.setString(6, person.getFatherID()); //can be null
      stmt.setString(7, person.getMotherID()); //can be null
      stmt.setString(8, person.getSpouseID()); //can be null

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
   * Generate the parents recursively to fill generations
   * @param mainPerson
   * @param genToMake
   * @param childBirthYear
   * @param eventDao
   * @throws DataAccessException
   */
  public void makeGenerations(PersonModel mainPerson, int genToMake, int childBirthYear, EventDao eventDao) throws DataAccessException {
    //make this recursive (because it takes in the number of gens to make each time, just update that and recurse up?
    //make a mother & father (and assign them to the kid)
    //make them each other's spouses
    PersonModel father = makeParent(mainPerson, "m"); //male parent
    PersonModel mother = makeParent(mainPerson, "f"); //female parent

    updateFamilyMember(mother.getPersonID(), father, "SpouseID");
    updateFamilyMember(father.getPersonID(), mother, "SpouseID");

    //then do the event stuff
    //we need to generate 3 events for each parent (assuming we have already generated the events for the main user)
    int birthDate = eventDao.makeEventsForParents(father, mother, childBirthYear);

    genToMake--;
    if(genToMake > 0){
      makeGenerations(mother, genToMake, birthDate, eventDao);
      makeGenerations(mother, genToMake, birthDate, eventDao);
    }
  }



  /**
   * Method to make a parent person and assign it into the child that was passed in
   * @param child
   * @param gender
   * @return
   * @throws DataAccessException
   */
  public PersonModel makeParent(PersonModel child, String gender) throws DataAccessException {

    //need to get a first and last name, make a personID, set their personID into the child's mother section
    //insert that person into the person table, return that personModel;

    String firstName = "";
    String updateID = "";
    String lastName = "";

    if(gender.equals("f")){
      firstName = femaleNames.getRandomName();
      updateID = "MotherID";
    } else {
      firstName = maleNames.getRandomName();
      updateID = "FatherID";
    }
//
    lastName = lastNames.getRandomName();

//    //test stuff
//    firstName = "bob";
//    lastName = "cratchet";


    String newPersonID =UUID.randomUUID().toString();

    //put the parent's personID into the child's place for that in the table
    updateFamilyMember(newPersonID, child, updateID);

    //now we need to take this new person's info, put it into a PersonModel, then insert them into the table
    PersonModel newMember = new PersonModel(newPersonID, child.getUsername(), firstName, lastName, gender, "", "", "");
    //they won't have a mother, father, or spouse yet


    addPerson(newMember);

    return newMember;
  }


  public void updateFamilyMember(String familyMemberID, PersonModel rootPerson, String memberType) throws DataAccessException {
    try{
      Statement stmt = null;
      try{
        //now update the PERSONID value for one of the family members
        String sqlite = "UPDATE Person\n" + "Set " + memberType + " = '" + familyMemberID + "' "
                + "WHERE personID = '" + rootPerson.getPersonID() + "'";
        stmt = conn.createStatement();
        stmt.executeUpdate(sqlite);
      } finally {
        if(stmt != null){
          stmt.close();
        }
      }

    } catch (SQLException e){
      throw new DataAccessException("Update Family Member Failed");
    }
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
  public ArrayList<PersonModel> getUsernameInfo (String Username) throws DataAccessException { //or should this be a usermodel object? --> i can change this later if need be

    //query the person table based on the username
    //return all the people that are associated with that username
    //add them to an array, then return that

    ArrayList<PersonModel> personArray = new ArrayList();
    PersonModel Person;
    ResultSet rs = null;
    String sql = "SELECT * FROM Person WHERE Username = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, Username);
      rs = stmt.executeQuery();
      while(rs.next()){

        Person = new PersonModel(rs.getString("PersonID"), rs.getString("Username"),
                rs.getString("FirstName"), rs.getString("LastName"),
                rs.getString("Gender"),rs.getString("FatherID"),
                rs.getString("MotherID"), rs.getString("SpouseID"));
        personArray.add(Person);
      }
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error getting a person from a username");
    } finally {
      if(rs != null){
        try{
          rs.close();
        } catch (SQLException e){
          e.printStackTrace();
        }
      }
    }

    if(!personArray.isEmpty()){
      return personArray;
    }
    return null;
  }

  /**
   * take in a user and then delete ALL THE PEOPLE ASSOCIATED WITH THAT USERNAME
   * @param Username
   */
  public void deleteUsernameData(String Username) throws DataAccessException {

    String sql = "DELETE FROM Person WHERE Username = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, Username);
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error deleting all people from a username");
    }

  }
}
