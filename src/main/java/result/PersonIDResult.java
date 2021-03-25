package result;

import model.PersonModel;

/**
 * Class for Person ID Results
 */
public class PersonIDResult {

  private String associatedUsername;
  private String personID;
  private String firstName;
  private String lastName;
  private String gender;
  private String fatherID; //can be null
  private String motherID; //can be null
  private String spouseID; //can be null

  private String message;
  private boolean success; //which will always be true in this case

  /**
   * Constructor for Successful Person ID Results
   * @param associatedUsername
   * @param personID
   * @param firstName
   * @param lastName
   * @param gender
   * @param fatherID
   * @param motherID
   * @param spouseID
   * @param success
   */
  public PersonIDResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
    this.associatedUsername=associatedUsername;
    this.personID=personID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
    this.success=success;
  }

  public PersonIDResult(PersonModel person){
    this.associatedUsername = person.getUsername();
    this.personID = person.getPersonID();
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.gender = person.getGender();
    this.fatherID = person.getFatherID();
    this.motherID = person.getMotherID();
    this.spouseID = person.getSpouseID();
    this.success = true;
  }

  public PersonIDResult(PersonModel person, boolean success){
    this.associatedUsername = person.getUsername();
    this.personID = person.getPersonID();
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.gender = person.getGender();
    this.fatherID = person.getFatherID();
    this.motherID = person.getMotherID();
    this.spouseID = person.getSpouseID();
    this.success = success;
  }

  /**
   * Constructor for failed Person ID Results
   * @param message
   * @param success
   */
  public PersonIDResult(String message, boolean success){
    this.message=message;
    this.success=success;
  }

  public String getUsername() {
    return associatedUsername;
  }

  public void setUsername(String associatedUsername) {
    this.associatedUsername=associatedUsername;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName=firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName=lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender=gender;
  }

  public String getFatherID() {
    return fatherID;
  }

  public void setFatherID(String fatherID) {
    this.fatherID=fatherID;
  }

  public String getMotherID() {
    return motherID;
  }

  public void setMotherID(String motherID) {
    this.motherID=motherID;
  }

  public String getSpouseID() {
    return spouseID;
  }

  public void setSpouseID(String spouseID) {
    this.spouseID=spouseID;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
