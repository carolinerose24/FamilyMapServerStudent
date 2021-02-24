package result;

import model.EventModel;

public class EventResult extends Result{

  private EventModel[] eventsList;
  private boolean success;

  public EventResult(String message, EventModel[] eventsList, boolean success) {
    super(message);
    this.eventsList=eventsList;
    this.success=success;
  }

  public EventModel[] getEventsList() {
    return eventsList;
  }

  public void setEventsList(EventModel[] eventsList) {
    this.eventsList=eventsList;
  }

  @Override
  public boolean isSuccess() {
    return success;
  }

  @Override
  public void setSuccess(boolean success) {
    this.success=success;
  }
}
