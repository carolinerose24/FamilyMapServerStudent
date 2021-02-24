package result;

public class PersonIDResult extends Result {

  private String username;
  private String personID;
  private String firstName;
  private String lastName;
  private String gender;
  private String fatherID; //can be null
  private String motherID; //can be null
  private String spouseID; //can be null
  private boolean success; //which will always be true in this case

  public PersonIDResult(String message, String username, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, boolean success) {
    super(message);
    this.username=username;
    this.personID=personID;
    this.firstName=firstName;
    this.lastName=lastName;
    this.gender=gender;
    this.fatherID=fatherID;
    this.motherID=motherID;
    this.spouseID=spouseID;
    this.success=success;
  }
}
