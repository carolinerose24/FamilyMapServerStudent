package result;

/**
 * Class for Clear Results
 */
public class ClearResult {

  private String message;
  private boolean success;

  /**
   * Constructor for Clear Results
   * @param message
   * @param success
   */
  public ClearResult(String message, boolean success) {
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
