package model;

import java.util.Objects;

/**
 * Model class for User Objects
 */
public class UserModel {

  private String username;
  private String personID;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private String gender;

  /**
   * Constructor for User Objects
   * @param username
   * @param personID
   * @param password
   * @param email
   * @param firstName
   * @param lastName
   * @param gender
   */
  public UserModel(String username, String personID, String password, String email, String firstName, String lastName, String gender) {
    this.username=username;
    this.personID=personID;
    this.password=password;
    this.email=email;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public String getPersonID() {
    return personID;
  }

  public void setPersonID(String personID) {
    this.personID=personID;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password=password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email=email;
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

  /**
   * Equals Override Method for comparing Users
   * @param o
   * @return true/false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {return false;}
    UserModel userModel=(UserModel) o;
    return username.equals(userModel.username) &&
            personID.equals(userModel.personID) &&
            password.equals(userModel.password) &&
            email.equals(userModel.email) &&
            firstName.equals(userModel.firstName) &&
            lastName.equals(userModel.lastName) &&
            gender.equals(userModel.gender);
  }

}
