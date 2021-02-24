package result;

import model.EventModel;
import request.EventRequest;

/**
 * Class for Event Results
 */
public class EventResult {

  private EventModel[] eventsList;

  private String message;
  private boolean success;

  /**
   * Constructor for Successful Event Results
   * @param eventsList
   * @param success
   */
  public EventResult(EventModel[] eventsList, boolean success) {
    this.eventsList=eventsList;
    this.success=success;
  }

  /**
   * Constructor for failed Event Results
   * @param message
   * @param success
   */
  public EventResult(String message, boolean success){
    this.message=message;
    this.success=success;
  }

  public EventModel[] getEventsList() {
    return eventsList;
  }

  public void setEventsList(EventModel[] eventsList) {
    this.eventsList=eventsList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message=message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success=success;
  }
}
