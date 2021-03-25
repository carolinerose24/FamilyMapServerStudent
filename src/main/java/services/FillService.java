package services;

import dataaccess.*;
import model.PersonModel;
import model.UserModel;
import request.FillRequest;
import result.FillResult;
import result.RegisterResult;

import java.sql.Connection;

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

    Database db = new Database();
    FillResult fRes;
    try{
      db.openConnection();
      Connection conn = db.getConnection();
      //need user, people, event
      UserDao newUser = new UserDao(conn);
      PersonDao newPerson = new PersonDao(conn);
      EventDao newEvent = new EventDao(conn);

      //go into USER TABLE and see if this user exists (FIND USER by username)
      //if exists, go into PERSON TABLE and delete all people associated with that username
      //also delete all events associated with that username
      //Generate USER events
      //Generate family/events to a certain generation

      UserModel user = newUser.findUser(request.getUsername());
      if(user == null){
        fRes = new FillResult("Error: Invalid username or generations parameter", false);
        db.closeConnection(false);
      } else {
        newPerson.deleteUsernameData(request.getUsername());
        //deletes their own person data and other's person data
        newEvent.deleteUsernameEventsData(request.getUsername());
        //now delete all their events and all family members events

        //now do what register user does
        //add yourself to Person table
        PersonModel newPersonModel = new PersonModel(user);
        newPerson.addPerson(newPersonModel);
        int year = newEvent.makeEventsForUser(newPersonModel); //make and add 3 events for the user
        newPerson.makeGenerations(newPersonModel, request.getGenerations(), year, newEvent); //make people and event for family

        //need to tally up how many persons and events were added to the DB

        double numPeople = 0;
        for(double i = 1; i <= request.getGenerations(); i++){
          numPeople = numPeople + Math.pow(2.0, i);
        }
        ++numPeople;

        int people = (int)numPeople;
        int events = people*3;
        String message = "Successfully added " + people + " persons and " + events + " events to the database.";
        fRes = new FillResult(message, true);

        //define fRES HERE
        db.closeConnection(true);
      }
    } catch(DataAccessException e){
      fRes = new FillResult("Error: Internal service error", false);
      try {
        db.closeConnection(false);
      } catch (DataAccessException ex) {
        ex.printStackTrace(); //shouldn't get this error a lot?
      }
    }
    //checks if the username is already in the DB
    //deletes all data associated with that username (if any)
    //makes new data
    //returns just a message, not any of that data (message with those specific numbers)

    //errors:
    //Internal service error
    //Invalid username or generations parameter

    return fRes;
  }
}
