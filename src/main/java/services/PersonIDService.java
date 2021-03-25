package services;

import dataaccess.*;
import model.AuthTokenModel;
import model.PersonModel;
import request.PersonIDRequest;
import result.PersonIDResult;
import result.RegisterResult;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;

/**
 * Class for Person ID Service
 */
public class PersonIDService {

  /**
   * Function to get a single person object from a person ID
   * @param request a PersonIDRequest object
   * @return a single person object
   */
  public PersonIDResult personIDInformation(PersonIDRequest request){

    //open connection to db,
    //check the auth token--> see which username it is associated with
    // look for person based on personID,
    Database db = new Database();
    PersonIDResult perIDRes;
    try{
      db.openConnection();
      Connection conn = db.getConnection();
      PersonDao newPerson = new PersonDao(conn);
      AuthTokenDao newAuth = new AuthTokenDao(conn);

      //check that the authtoken exists in the table, get back which username it applies to
      AuthTokenModel aToken = newAuth.getAuthToken(request.getAuthtoken());
      if(aToken == null){
        //bad authtoken,
        perIDRes = new PersonIDResult("Error: Invalid auth token", false);
      } else {
        //now we need to check that that username owns that personID
        String username = aToken.getUsername();
        PersonModel foundPerson = newPerson.findPerson(request.getPersonID());
        //if this returns null, there is no one with this personID in the table
        if(foundPerson == null){
          //there is no one of that personID, return
          perIDRes = new PersonIDResult("Error: Invalid personID parameter", false);
          //else we need to check if that person is owned by that user
        } else if(!foundPerson.getUsername().equals(username)){
          //they don't own that person
          perIDRes = new PersonIDResult("Error: Requested person does not belong to this user", false);
        } else { //all is well
          perIDRes = new PersonIDResult(foundPerson, true);
        }
        //OTHERWISE EVERYTHING IS RIGHT
      }
      db.closeConnection(true);
    } catch (DataAccessException e){
      perIDRes = new PersonIDResult("Error: Internal service error", false);
      try {
        db.closeConnection(false);
      } catch (DataAccessException ex) {
        ex.printStackTrace(); //shouldn't get this error a lot?
      }
    }

    return perIDRes;
  }
}
