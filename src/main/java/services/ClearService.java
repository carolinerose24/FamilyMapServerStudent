package services;

import result.ClearResult;

/**
 * Class for Clear Service
 */
public class ClearService {

  //this doesn't take in a clear request object --> because it doesn't need any additional data
  //but it should still return a result

  /**
   * Function to clear the database
   * @return Clear Result
   */
  public ClearResult clearDatabase(){

    //don't need a clear request OR result specific class;

    //if fails --> error is: Internal service error

//    String message = "Clear succeeded.";
//    boolean success = true;
//    return Result(message, success);
    return null;
  }
}
