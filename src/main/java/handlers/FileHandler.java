package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class FileHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {


    try{

      //restrict it to GET requests
      //get the url from exchange object
      //that path will tell you which file we want
      // plain / wants index.html
      String urlPath = exchange.getRequestURI().toString();
      String filePath = "web" + urlPath;
      //for security --> restrict what urlPath could be, but don't need to do that here

      //then create a file object and check if the file exists (file.exists())
      //return a 404 if not found
      //if file exists, read the file and write it to exchange's outputStream
      File file = new File(filePath);
      if(file.exists()){
        OutputStream respBody = exchange.getResponseBody();
        Files.copy(file.toPath(), respBody);
        //return success file code
      } else {
        //return the 404 error
      }












    } catch (IOException e){

      e.printStackTrace();
    }

  }
  //this is just a web browser looking for the home page


  /**
   * Stuff this needs to handle
   * /
   * /index.html
   * /favicon.ico
   * /css/main.css
   * --> ALL OF THESE ARE IN THE WEB FILE
   *
   */

}
