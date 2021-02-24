package request;

import model.EventModel;
import model.PersonModel;
import model.UserModel;

public class LoadRequest {

  private UserModel[] userList;
  private PersonModel[] personList;
  private EventModel[] eventList;

  public LoadRequest(UserModel[] userList, PersonModel[] personList, EventModel[] eventList) {
    this.userList=userList;
    this.personList=personList;
    this.eventList=eventList;
  }

  public UserModel[] getUserList() {
    return userList;
  }

  public void setUserList(UserModel[] userList) {
    this.userList=userList;
  }

  public PersonModel[] getPersonList() {
    return personList;
  }

  public void setPersonList(PersonModel[] personList) {
    this.personList=personList;
  }

  public EventModel[] getEventList() {
    return eventList;
  }

  public void setEventList(EventModel[] eventList) {
    this.eventList=eventList;
  }
}
