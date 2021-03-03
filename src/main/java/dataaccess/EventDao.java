package dataaccess;

import model.EventModel;
import model.UserModel;

import java.sql.*;

/**
 * Event Data Access class to access the Event table
 */
public class EventDao {


  private final Connection conn;

  /**
   * Constructor to initialize the Connection Object
   * @param conn
   */
  public EventDao(Connection conn)
  {
    this.conn = conn;
  }


  /**
   * Add an event to a person
   * @param event
   * @throws DataAccessException
   */
  public void addEvent(EventModel event) throws DataAccessException {
    String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
            "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?);";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, event.getEventID());
      stmt.setString(2, event.getUsername());
      stmt.setString(3, event.getPersonID());
      stmt.setFloat(4, event.getLatitude());
      stmt.setFloat(5, event.getLongitude());
      stmt.setString(6, event.getCountry());
      stmt.setString(7, event.getCity());
      stmt.setString(8, event.getEventType());
      stmt.setInt(9, event.getYear());
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new DataAccessException("Error when adding an event");
    }
  }


  /**
   * Finds a specific event from an eventID
   * @param event
   * @return EventModel object
   * @throws DataAccessException
   */
  public EventModel find(EventModel event) throws DataAccessException {
    EventModel Event;
    ResultSet rs = null;
    String sql = "SELECT * FROM Events WHERE EventID = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, event.getEventID());
      rs = stmt.executeQuery();
      if (rs.next()) {
        Event = new EventModel(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                rs.getInt("Year"));
        return Event;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error while finding event");
    } finally {
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }
    return null;
  }



  /**
   * Clears the Event table
   * @throws DataAccessException
   */
  public void clearEventTable() throws DataAccessException {
    try (Statement stmt = conn.createStatement()){
      String sql = "DELETE FROM Event";
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      throw new DataAccessException("SQL Error encountered while clearing tables");
    }
  }




  /**
   * TAKE A USERNAME AND RETURN all the events associated with that person
   * @param Username
   * @return
   */
  public UserModel getUsernameEventsData (String Username){ //or should this be a usermodle object?

    //should this return all the events associated with this user? yes
    return null;
  }

  /**
   * take in a user and then delete ALL THE events ASSOCIATED WITH THAT USERNAME
   * @param user
   */
  public void deleteUsernameEventsData(UserModel user){

  }
}
