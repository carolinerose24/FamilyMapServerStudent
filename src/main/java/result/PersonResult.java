package result;

import model.PersonModel;

public class PersonResult {

  //array of person objects
  private PersonModel[] familyList;
  private boolean success; //does this need getters/setters? it should always be true at this point

  public PersonResult(PersonModel[] familyList, boolean success) {
    this.familyList=familyList;
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
}
