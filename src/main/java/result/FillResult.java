package result;

/**
 * Class for Fill Results
 */
public class FillResult {

  private String message;
  private boolean success;

  /**
   * Constructor for Fill Results
   * @param message
   * @param success
   */
  public FillResult(String message, boolean success) {
    this.message=message;
    this.success=success;
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
