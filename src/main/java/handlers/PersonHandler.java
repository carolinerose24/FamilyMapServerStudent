package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dataaccess.AuthTokenDao;
import generation.Deserialize;
import request.PersonIDRequest;
import request.PersonRequest;
import result.PersonIDResult;
import result.PersonResult;
import result.RegisterResult;
import services.PersonIDService;
import services.PersonService;

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
          //we have the authToken


          //now check if it is a person or personID function
          String RequestUrl = exchange.getRequestURI().toString();
          if(RequestUrl.equals("/person") || RequestUrl.equals("/person/")){

            //we now need to call the Person method
            PersonRequest personReq = new PersonRequest(authToken);

            PersonService personServ = new PersonService();
            PersonResult personRes = personServ.getFamilyMembers(personReq);



            if(personRes.isSuccess()){
              //it returned TRUE
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else if (!personRes.getMessage().equals("Error: Internal service error")){ //a user error
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            } else {
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            }












          } else {
            //we need to read in the second part of it
            //we need the ninth character until the end
            //do we need to check if there is anything else in there? like check for another /
            //idt that auth tokens ever have a slash in them
            String personID = RequestUrl.substring(8);
            //this either is the personID or is wrong
            PersonIDRequest perIDReq = new PersonIDRequest(personID, authToken);
            PersonIDService perIDSer = new PersonIDService();
            PersonIDResult perIDRes = perIDSer.personIDInformation(perIDReq);

            //now we need to send that result up to the user
            if(perIDRes.isSuccess()){
              //it returned TRUE
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            } else if (!perIDRes.getMessage().equals("Error: Internal service error")){ //a user error
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            } else {
              exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            }
            //now we need to print it us to the user

            String respData = Deserialize.toJsonDeserialize(perIDRes);
            OutputStream respBody = exchange.getResponseBody();
            writeString(respData, respBody);
            respBody.close();

          }















        } else {
          //they didn't send an auth token, so it was an invalid auth token
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          //we don't care rn which kind of person request it was, so return either
          PersonResult pRes = new PersonResult("Error: Invalid auth token", false);
          String respData = Deserialize.toJsonDeserialize(pRes);
          OutputStream respBody = exchange.getResponseBody();
          writeString(respData, respBody);
          respBody.close();
        }
      }

//      if(!success){
//        //if it was bad
//        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//        exchange.getResponseBody().close();
//      }
    } catch (IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0); //this is the internal error
      PersonResult pRes = new PersonResult("Error: Internal service error", false);
      String respData = Deserialize.toJsonDeserialize(pRes);
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
