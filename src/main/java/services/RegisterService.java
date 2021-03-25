package services;

import dataaccess.*;
import model.AuthTokenModel;
import model.PersonModel;
import model.UserModel;
import request.RegisterRequest;
import result.RegisterResult;

import java.sql.Connection;

/**
 * Class for Register Service
 */
public class RegisterService {

  public RegisterService(){

  }

  //only method in here should take in a request object and give out a result object

  /**
   * Function to register a user
   * @param request Register Request object
   * @return some information including an auth token
   */
  public static RegisterResult registerUser(RegisterRequest request){
    Database db = new Database();
    RegisterResult result;
    try {
      db.openConnection();
      Connection conn = db.getConnection();
      UserDao newUser = new UserDao(conn);
      PersonDao newPerson = new PersonDao(conn);
      EventDao newEvent = new EventDao(conn);
//      AuthTokenDao newAuth = new AuthTokenDao(conn);
//      System.out.println("Made the DAO objects with the connection");

//      newUser.clearUserTable();
//      newPerson.clearPersonTable();
//      newEvent.clearEventTable();
//      newAuth.clearAuthTokenTable();



      UserModel newUserModel = new UserModel(request); //personID was generated in userModel
      newUser.addUser(newUserModel); //shouldn't have any empty values
      PersonModel newPersonModel = new PersonModel(newUserModel); //transfers all the values from user to person, sets parents to null for now
      newPerson.addPerson(newPersonModel); //adds that person to the table
//      System.out.println("Adds the new user to User and Person");


      //now we need to make 4 generations: (so the user + 4 layers above)
      //and populate the person table with them
      int year = newEvent.makeEventsForUser(newPersonModel); //make 3 events for the user (doesn't include death)
      newPerson.makeGenerations(newPersonModel, 4, year, newEvent);
//      System.out.println("Adds the 4 generations");

      //then get a new auth token and return it
      AuthTokenModel aModel = new AuthTokenModel(newUserModel.getUsername());
      result = new RegisterResult(aModel.getAuthToken(), newUserModel.getUsername(), newUserModel.getPersonID(), true);
              //auth token, username, personID, success

//      System.out.println("gets an auth token");


      db.closeConnection(true);
    } catch (DataAccessException e){
      result = new RegisterResult(e.getMessage(), false);
      try {
        db.closeConnection(false);
      } catch (DataAccessException ex) {
        ex.printStackTrace(); //shouldn't get this error a lot?
      }
    }

//    System.out.println("Returns the result");
    return result;

    //else just return a RegisterResult

    //errors: Request property missing or has invalid value
    //OR: Username already taken by another user
    //OR: Internal server error
    //make a new instance of an errorResult and then return it

    //else interact with the DAO and the DB



    //make a usermodel with the info (ret usermodel)
    //make a personmodel (or do i need to make a person first for this database to work?
    //generate the 4 default generations

    //make a new auth token

    //THEN make a RegisterResult from all of this combined data
  }







  //one public method in each that performs the web api operation
  //perform functions of server
  //each web api has a class

  //input: request object with info from client
  //output: result object (java objects)

  //also make a base class that has the error stuff (
  //non null gets serialized, null don't

  //user inheritance
  //error stuff in base class, other result classes inherit from that

  //has requests: register, login, fill(has url info-->username,generations), load, person, event
    //fill doesn't have a request body BUT it still uses url info --> so still make one
  //doesn't: clear,






}
