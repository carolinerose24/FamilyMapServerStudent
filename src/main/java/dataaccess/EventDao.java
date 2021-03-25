package dataaccess;

import generation.Location;
import generation.LocationData;
import generation.Serialize;
import model.EventModel;
import model.PersonModel;
import model.UserModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Event Data Access class to access the Event table
 */
public class EventDao {


  private final Connection conn;
  private LocationData locations;

  /**
   * Constructor to initialize the Connection Object
   * @param conn
   */
  public EventDao(Connection conn)
  {
    this.conn = conn;
    locations =Serialize.serializeLocationList("json/snames.json");
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
   * Adds the events for the root user when they register
   * @param user
   * @return
   * @throws DataAccessException
   */
  public int makeEventsForUser(PersonModel user) throws DataAccessException {
    int birthYear = 1970;

    Location eventLoc1 = locations.getRandomLocation();
    float lat1 = Float.parseFloat(eventLoc1.getLatitude());
    float longitude1 = Float.parseFloat(eventLoc1.getLongitude());
    EventModel firstEvent = new EventModel(UUID.randomUUID().toString(), user.getUsername(), user.getPersonID(),
            lat1, longitude1, eventLoc1.getCountry(), eventLoc1.getCity(), "Birth", birthYear);

    addEvent(firstEvent);

    Location eventLoc2 = locations.getRandomLocation();
    float lat2 = Float.parseFloat(eventLoc2.getLatitude());
    float longitude2 = Float.parseFloat(eventLoc2.getLongitude());
    EventModel secondEvent = new EventModel(UUID.randomUUID().toString(), user.getUsername(), user.getPersonID(),
            lat2, longitude2, eventLoc2.getCountry(), eventLoc2.getCity(), "Family Trip", birthYear+15);

    addEvent(secondEvent);

    Location eventLoc3 = locations.getRandomLocation();
    float lat3 = Float.parseFloat(eventLoc3.getLatitude());
    float longitude3 = Float.parseFloat(eventLoc3.getLongitude());
    EventModel thirdEvent = new EventModel(UUID.randomUUID().toString(), user.getUsername(), user.getPersonID(),
            lat3, longitude3, eventLoc3.getCountry(), eventLoc3.getCity(), "College", birthYear+18);

    addEvent(thirdEvent);

    return birthYear;
  }


  /**
   * Method for generating a birth, marriage, and death date for two parents
   * @param father
   * @param mother
   * @param childBirthYear
   * @return
   * @throws DataAccessException
   */
  public int makeEventsForParents(PersonModel father, PersonModel mother, int childBirthYear) throws DataAccessException {
    //Mother's birth date should be ~25 years before the child's birth date
    //Father's birth date should also be around there
    int birthYearOfParents = childBirthYear - 27;


    //mother's birth date
    Location motherLoc1 = locations.getRandomLocation();
    float lat1M = Float.parseFloat(motherLoc1.getLatitude());
    float longitude1M = Float.parseFloat(motherLoc1.getLongitude());
    EventModel motherBirth = new EventModel(UUID.randomUUID().toString(), mother.getUsername(), mother.getPersonID(),
            lat1M, longitude1M, motherLoc1.getCountry(), motherLoc1.getCity(), "Birth", birthYearOfParents);
    addEvent(motherBirth);

    //father's birth date
    Location fatherLoc1 = locations.getRandomLocation();
    float lat1F = Float.parseFloat(fatherLoc1.getLatitude());
    float longitude1F = Float.parseFloat(fatherLoc1.getLongitude());
    EventModel fatherBirth = new EventModel(UUID.randomUUID().toString(), mother.getUsername(), mother.getPersonID(),
            lat1F, longitude1F, fatherLoc1.getCountry(), fatherLoc1.getCity(), "Birth", birthYearOfParents);
    addEvent(fatherBirth);



    //their marriage date should be when they are like early 20s
    Location eventLoc2 = locations.getRandomLocation();
    float lat2MF = Float.parseFloat(eventLoc2.getLatitude());
    float longitude2MF = Float.parseFloat(eventLoc2.getLongitude());
    EventModel motherMarriage = new EventModel(UUID.randomUUID().toString(), mother.getUsername(), mother.getPersonID(),
            lat2MF, longitude2MF, eventLoc2.getCountry(), eventLoc2.getCity(), "Marriage", birthYearOfParents+25);
    EventModel fatherMarriage = new EventModel(UUID.randomUUID().toString(), father.getUsername(), father.getPersonID(),
            lat2MF, longitude2MF, eventLoc2.getCountry(), eventLoc2.getCity(), "Marriage", birthYearOfParents+25);
    addEvent(motherMarriage);
    addEvent(fatherMarriage);


    //death in the 70's/80's
    Location motherLoc3 = locations.getRandomLocation();
    float lat3M = Float.parseFloat(motherLoc3.getLatitude());
    float longitude3M = Float.parseFloat(motherLoc3.getLongitude());
    EventModel motherDeath = new EventModel(UUID.randomUUID().toString(), mother.getUsername(), mother.getPersonID(),
            lat3M, longitude3M, motherLoc3.getCountry(), motherLoc3.getCity(), "Death", birthYearOfParents+75);
    addEvent(motherDeath);

    Location fatherLoc3 = locations.getRandomLocation();
    float lat3F = Float.parseFloat(fatherLoc3.getLatitude());
    float longitude3F = Float.parseFloat(fatherLoc3.getLongitude());
    EventModel fatherDeath = new EventModel(UUID.randomUUID().toString(), mother.getUsername(), mother.getPersonID(),
            lat3F, longitude3F, fatherLoc3.getCountry(), fatherLoc3.getCity(), "Death", birthYearOfParents+70);
    addEvent(fatherDeath);


    //TODO make the years between these three events random within limits
    //like menopause @~40
    //deaths between 30-100
    //could make locations match more/be more consistent with other events of that person/this spouse/kid

    return childBirthYear-27;
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
  public ArrayList<EventModel> getUsernameEventsData (String Username) throws DataAccessException { //or should this be a usermodle object?
    //should this return all the events associated with this user? yes

    ArrayList<EventModel> eventArray = new ArrayList();
    EventModel Event;
    ResultSet rs = null;
    String sql = "SELECT * FROM Events WHERE Username = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, Username);
      rs = stmt.executeQuery();
      while (rs.next()) {
        Event = new EventModel(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                rs.getInt("Year"));
        eventArray.add(Event);
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DataAccessException("Error while getting array of events");
    } finally {
      if(rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

    }
    if(!eventArray.isEmpty()){
      return eventArray;
    }
    return null;
  }

  /**
   * take in a user and then delete ALL THE events ASSOCIATED WITH THAT USERNAME
   * @param Username
   */
  public void deleteUsernameEventsData(String Username) throws DataAccessException {
    String sql = "DELETE FROM Event WHERE Username = ?;";
    try (PreparedStatement stmt = conn.prepareStatement(sql)){
      stmt.setString(1, Username);
      stmt.executeUpdate(sql);
    } catch (SQLException e){
      e.printStackTrace();
      throw new DataAccessException("Error deleting all people from a username");
    }

  }
}
