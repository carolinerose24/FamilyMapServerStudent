package services;

import dataaccess.*;
import model.AuthTokenModel;
import model.PersonModel;
import model.UserModel;
import request.RegisterRequest;
import result.RegisterResult;

/**
 * Class for Register Service
 */
public class RegisterService {

  //only method in here should take in a request object and give out a result object

  /**
   * Function to register a user
   * @param request Register Request object
   * @return some information including an auth token
   */
  public RegisterResult registerUser(RegisterRequest request){
    Database db = new Database();
    RegisterResult result;
    try {
      db.openConnection();
      UserDao newUser = new UserDao(db.getConnection());
      PersonDao newPerson = new PersonDao(db.getConnection());
      AuthTokenDao newAToken = new AuthTokenDao(db.getConnection());
      EventDao newEvent = new EventDao(db.getConnection());

      //user the data is register request to make a usermodel and a personmodel
      //then call the add user and person methods with the models

      //return authtoken, username, personID, success
//      RegisterResult rslt = new RegisterResult();
//      return rslt;

      UserModel newUserModel = new UserModel(request); //personID was generated in userModel
      newUser.addUser(newUserModel); //shouldn't have any empty values

      PersonModel newPersonModel = new PersonModel(newUserModel); //transfers all the values from user to person, sets parents to null for now
      newPerson.addPerson(newPersonModel); //adds that person to the table



      //now we need to make 4 generations: (so the user + 4 layers above)
      //and populate the person table with them



      //then we need to


      AuthTokenModel aModel = new AuthTokenModel(newUserModel.getUsername());
      result = new RegisterResult(aModel.getAuthToken(), newUserModel.getUsername(), newUserModel.getPersonID(), true);
              //auth token, username, personID, success


      db.closeConnection(true);
    } catch (DataAccessException e){
      result = new RegisterResult("error - Register service didn't work", false);
      try {
        db.closeConnection(false);
      } catch (DataAccessException ex) {
        ex.printStackTrace();
      }
    }
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
