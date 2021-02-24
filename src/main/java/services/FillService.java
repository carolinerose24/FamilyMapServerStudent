package services;

import request.FillRequest;
import result.FillResult;

/**
 * Class for Fill Service
 */
public class FillService {


  /**
   * Function to fill in the specificed generations for a user
   * @param request a FillRequest object
   * @return success or failure message
   */
  public FillResult fillGenerations(FillRequest request){

    //checks if the username is already in the DB
    //deletes all data associated with that username (if any)
    //makes new data
    //returns just a message, not any of that data (message with those specific numbers)

    //errors:
    //Internal service error
    //Invalid username or generations parameter

    return null;
  }
}
