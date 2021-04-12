package services;

import dataaccess.AuthTokenDao;
import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDao;
import model.AuthTokenModel;
import model.PersonModel;
import request.PersonRequest;
import result.PersonResult;

import java.sql.Connection;

/**
 * Class for Person Service
 */
public class PersonService {

  /**
   * Function to get all the people associated with a user
   * @param request PersonRequest
   * @return all family members of the user
   */
  public PersonResult getFamilyMembers(PersonRequest request){
    //check authtoken
    //see if there are any people in person that have the same username associated with them
    //return an array of all of those people
    Database db = new Database();
    PersonResult personRes;
    try{
      db.openConnection();
      Connection conn = db.getConnection();
      PersonDao newPerson = new PersonDao(conn);
      AuthTokenDao newAuth = new AuthTokenDao(conn);

      AuthTokenModel aToken = newAuth.getAuthToken(request.getAuthtoken());
      if(aToken == null){
        personRes = new PersonResult("Error: Invalid auth token", false);
      } else {
        String username = aToken.getUsername();
        PersonModel[] foundPeople = newPerson.getUsernameInfo(username);
        if(foundPeople == null){
          //not sure what to do here,
        }

        personRes = new PersonResult(foundPeople, true);
        db.closeConnection(true);

      }
    } catch (DataAccessException e){
      personRes = new PersonResult("Error: Internal service error", false);
      try{
        db.closeConnection(false);
      } catch (DataAccessException ex){
        ex.printStackTrace();
      }
    }

    return personRes;
  }
}
