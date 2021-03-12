package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    boolean success = false;
    try{
      if(exchange.getRequestMethod().toLowerCase().equals("get")){
        Headers reqHeader = exchange.getRequestHeaders();

        if(reqHeader.containsKey("Authorization")){ //if there is ANY auth token, check valid next
          String authToken = reqHeader.getFirst("Authorization");


          //now go the the database and see which user is associated with this auth token
          //if valid, then return stuff

          String respData = ""; //need to get this from somewhere, i think it should be json?
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          OutputStream respBody = exchange.getResponseBody();

          //write the json string to the output stream (small method below)
          writeString(respData, respBody);

          respBody.close();
          success = true;

        }
      }

      if(!success){
        //if it was bad
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        exchange.getResponseBody().close();
      }
    } catch (IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      exchange.getResponseBody().close();
      e.printStackTrace();
    }

  }

  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}
