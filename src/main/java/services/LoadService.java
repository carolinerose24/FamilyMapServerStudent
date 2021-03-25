package services;

import dataaccess.*;
import request.LoadRequest;
import result.ClearResult;
import result.LoadResult;

import java.sql.Connection;

/**
 * Class for Load Service
 */
public class LoadService {


  /**
   * Function to clear the database and load a specific user's data
   * @param request a LoadRequest Object
   * @return a success or failure message
   */
  public LoadResult loadData(LoadRequest request){
    Database db = new Database();
    LoadResult lres;
    try{
      ClearService cService = new ClearService();
      ClearResult cresult = cService.clearDatabase();
      if(!cresult.isSuccess()){ //if it fails, we need to say internal service error
        return lres = new LoadResult("Error: Internal service error", false);
      }
      //otherwise, just continue on with the clear tables

      db.openConnection();
      Connection conn = db.getConnection();
      UserDao newUser = new UserDao(conn);
      PersonDao newPerson = new PersonDao(conn);
      EventDao newEvent = new EventDao(conn);
      //make 3 daos, now we need to insert into each of them


      //fill this in after doing /person and /event








    } catch (DataAccessException e){

    }




    return null;
  }
}
