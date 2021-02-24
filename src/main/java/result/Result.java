package result;

public class Result {

  //have this be the superclass
  //all services just return a result (and it could also be an error result
  private String message;
  private boolean success;

  public Result(String message) {
    this.message=message;
    this.success=true;
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
