package services;

import request.RegisterRequest;
import result.RegisterResult;
import result.Result;

public class RegisterService {

  //only method in here should take in a request object and give out a result object

  /*
  * this method creates a new user account, generates 4 generations, logs ins, and returns auth token
  *
  *
  */
  public Result registerUser(RegisterRequest request){


    //if anypart of it is invalid, then return an errorResult
    //else just return a RegisterResult

    //errors: Request property missing or has invalid value
    //OR: Username already taken by another user
    //OR: Internal server error
    //make a new instance of an errorResult and then return it

    //else interact with the DAO and the DB


    return null;
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