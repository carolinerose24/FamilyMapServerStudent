package services;

import dataaccess.*;
import model.AuthTokenModel;
import model.EventModel;
import request.EventIDRequest;
import result.EventIDResult;
import result.PersonIDResult;

import java.sql.Connection;

/**
 * Class for Event ID Service
 */
public class EventIDService {

  /**
   * function to get a specific event object from an event ID
   * @param request a EventIDRequest object
   * @return Event ID Result
   */
  public EventIDResult getEvent(EventIDRequest request){
    Database db = new Database();
    EventIDResult eRes;
    try{
      db.openConnection();
      Connection conn = db.getConnection();
      AuthTokenDao newAuth = new AuthTokenDao(conn);
      EventDao newEvent = new EventDao(conn);

      AuthTokenModel aToken = newAuth.getAuthToken(request.getAuthtoken());
      if(aToken == null){
        //bad authToken
        eRes = new EventIDResult("Error: Invalid auth token", false);
      } else {
        //it was a correct a token
        String username = aToken.getUsername();
        EventModel foundEvent = newEvent.findEvent(request.getEventID());

        if(foundEvent == null){
          //no such event
          eRes = new EventIDResult("Error: Invalid eventID parameter", false);
        } else if(!foundEvent.getUsername().equals(username)){
          eRes = new EventIDResult("Error: Requested event does not belong to this user", false);
        } else {
          //all is well
          eRes = new EventIDResult(foundEvent);
        }
      }
      db.closeConnection(true);

    } catch (DataAccessException e){
      eRes = new EventIDResult("Error: Internal service error", false);
      try {
        db.closeConnection(false);
      } catch (DataAccessException ex) {
        ex.printStackTrace(); //shouldn't get this error a lot?
      }
    }


    return eRes;
  }
}
