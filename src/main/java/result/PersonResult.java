package result;

import model.PersonModel;

/**
 * Class for Person Results
 */
public class PersonResult {

  //array of person objects
  private PersonModel[] familyList;

  private String message;
  private boolean success; //does this need getters/setters? it should always be true at this point

  /**
   * Constructor for Successful Person Results
   * @param familyList
   * @param success
   */
  public PersonResult(PersonModel[] familyList, boolean success) {
    this.familyList=familyList;
    this.success=success;
  }

  /**
   * Constructor for failed Person Results
   * @param message
   * @param success
   */
  public PersonResult(String message, boolean success){
    this.message=message;
    this.success=success;
  }

  public PersonModel[] getFamilyList() {
    return familyList;
  }

  public void setFamilyList(PersonModel[] familyList) {
    this.familyList=familyList;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }
}
