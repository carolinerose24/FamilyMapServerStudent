package model;

import java.util.Objects;

/**
 * Model class for Person Objects
 */
public class PersonModel {

  private String personID;
  private String username;
  private String firstName;
  private String lastName;
  private String gender;
  private String fatherID;
  private String motherID;
  private String spouseID;

  /**
   * Constructor for the Person Class
   * @param personID
   * @param username
   * @param firstName
   * @param lastName
   * @param gender
   * @param fatherID
   * @param motherID
   * @param spouseID
   */
  public PersonModel(String personID, String username, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
    this.personID=personID;
    this.username=username;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
  }

  public PersonModel(UserModel user){
    this.personID = user.getPersonID(); //this would still be empty here?
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.gender = user.getGender();
    this.fatherID = ""; //new String(); does this do anything different from ""?
    this.motherID = "";
    this.spouseID = "";
  }



  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
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

  /**
   * Equals Override Method for comparing People
   * @param o
   * @return true/false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {return false;}
    PersonModel that=(PersonModel) o;
    return personID.equals(that.personID) &&
            username.equals(that.username) &&
            firstName.equals(that.firstName) &&
            lastName.equals(that.lastName) &&
            gender.equals(that.gender) &&
            Objects.equals(fatherID, that.fatherID) &&
            Objects.equals(motherID, that.motherID) &&
            Objects.equals(spouseID, that.spouseID);
  }


}
