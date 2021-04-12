package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.*;

public class DefaultHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {


    boolean success = false;
    try{

      //restrict it to GET requests
      if(exchange.getRequestMethod().toLowerCase().equals("get")){
        //doesn't need an auth token
        //doesn't have response data either

        //get the url from exchange object
        //that path will tell you which file we want
        // plain / wants index.html
        String urlPath = exchange.getRequestURI().toString();
        if(urlPath.equals("/") || urlPath == null){
          urlPath = "/index.html";
        }

        String filePath = "web" + urlPath;
        //for security --> restrict what urlPath could be, but don't need to do that here


        Path fPath = FileSystems.getDefault().getPath(filePath);
        File newFile = new File(String.valueOf(fPath));
        if(newFile.exists()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          Files.copy(Paths.get(filePath), exchange.getResponseBody());
          exchange.getResponseBody().close();
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
          Files.copy(Paths.get("web/HTML/404.html"), exchange.getResponseBody());
          exchange.getResponseBody().close();
//          success = true;
        }

      } else {
        //if it is not a get, then it should fail HERE
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        exchange.getResponseBody().close();
      }
//      if(!success){
//        //if it was bad
//        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//        exchange.getResponseBody().close();
//      }

    } catch (IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      exchange.getResponseBody().close();
      System.out.println("error --> catch block of default handler");
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
