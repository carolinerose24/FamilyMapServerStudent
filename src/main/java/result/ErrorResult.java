package result;

public class ErrorResult extends Result {


  //this just has a default return message
  //when we have an error, it should just fill in the specific details of the error?


  //need to look more into this --> maybe just have a general response class?


  private String message;
  private boolean success; //not sure if i need this??

  public ErrorResult(String message) {
    this.message="Error: " + message;
    this.success=false;
  }

}
