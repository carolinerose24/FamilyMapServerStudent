package services;

import result.Result;

public class ClearService {

  //this doesn't take in a clear request object --> because it doesn't need any additional data
  //but it should still return a result
  public Result clearDatabase(){

    //don't need a clear request OR result specific class;

    //if fails --> error is: Internal service error

//    String message = "Clear succeeded.";
//    return Result(message);
    return null;
  }
}
