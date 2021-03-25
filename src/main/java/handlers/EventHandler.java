package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import generation.Deserialize;
import request.EventIDRequest;
import result.EventIDResult;
import result.EventResult;
import result.PersonResult;
import services.EventIDService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {


  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try{
      if(exchange.getRequestMethod().toLowerCase().equals("get")){
        Headers reqHeader = exchange.getRequestHeaders();
        if(reqHeader.containsKey("Authorization")){
          String authToken = reqHeader.getFirst("Authorization");
          String RequestUrl = exchange.getRequestURI().toString();
          if(RequestUrl.equals("/event") || RequestUrl.equals("/event/")){
            //call the /event method
          } else {
            //event ID method
            String eventID = RequestUrl.substring(7);
            EventIDRequest eIDReq = new EventIDRequest(eventID, authToken);
            EventIDService eIDSer = new EventIDService();
            EventIDResult eIDRes = eIDSer.getEvent(eIDReq);

            if(eIDRes.isSuccess()){
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else if(!eIDRes.getMessage().equals("Error: Internal service error")){
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            } else {
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            }

            String respData = Deserialize.toJsonDeserialize(eIDRes);
            OutputStream respBody = exchange.getResponseBody();
            writeString(respData, respBody);
            respBody.close();

          }

        } else {
          //no auth token at all
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          EventResult eRes = new EventResult("Error: Invalid auth token", false);
          String respData = Deserialize.toJsonDeserialize(eRes);
          OutputStream respBody = exchange.getResponseBody();
          writeString(respData, respBody);
          respBody.close();
          exchange.getResponseBody().close();
        }

      } //else it is a bad request method
    } catch(IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0); //this is the internal error
      EventResult eRes = new EventResult("Error: Internal service error", false);
      String respData = Deserialize.toJsonDeserialize(eRes);
      OutputStream respBody = exchange.getResponseBody();
      writeString(respData, respBody);
      respBody.close();
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
