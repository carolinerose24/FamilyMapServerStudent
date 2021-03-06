package request;

/**
 * Class for Fill Requests
 */
public class FillRequest {

  private String username;
  private int generations;

  /**
   * Constructor for Fill Requests
   * @param username
   * @param generations
   */
  public FillRequest(String username, int generations) {
    this.username=username;
    this.generations=generations; //the default is 4 if they don't specify
  }

  public FillRequest(String username){
    this.username=username;
    this.generations=4;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username=username;
  }

  public int getGenerations() {
    return generations;
  }

  public void setGenerations(int generations) {
    this.generations=generations;
  }
}
