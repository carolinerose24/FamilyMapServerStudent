package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import generation.Deserialize;
import generation.Serialize;
import request.RegisterRequest;
import result.RegisterResult;
import services.RegisterService;

import java.io.*;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {


  @Override
  public void handle(HttpExchange exchange) throws IOException {
    //exchange has request info and response info
    //io exception because we are networking

    boolean success = false;

    try{
      if(exchange.getRequestMethod().toLowerCase().equals("post")){
//        Headers reqHeaders = exchange.getRequestHeaders();
        RegisterService regService = new RegisterService();


        //get request body
        InputStream reqBody = exchange.getRequestBody();
        //decode request body
        Reader json =  new InputStreamReader(reqBody);
        RegisterRequest regRequest = Serialize.SerializeRegisterRequest(json);
        //make a new register service
//        System.out.println("Made the register request");



        //delete everything from all the tables rn





//        RegisterResult regResult = new RegisterResult("asdfasdf", "sd", "er", true);
        RegisterResult regResult = regService.registerUser(regRequest);
//        System.out.println("Got back the register result");

        if(regResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          String respData = Deserialize.toJsonDeserialize(regResult);
          OutputStream respBody = exchange.getResponseBody();
          writeString(respData, respBody);
//          System.out.println(respBody);
          respBody.close();
          success = true;
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
          String errorMessage = "{\"message\" : \"" + regResult.getMessage() + "\"}";
          OutputStream respBody = exchange.getResponseBody();
          writeString(errorMessage, respBody);
          respBody.close();
        }




//        String respData = Deserialize.toJsonDeserialize(regResult);
//        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
//        OutputStream respBody = exchange.getResponseBody();
//        System.out.println(respBody);

//        //write the json string to the output stream (small method below)
//        writeString(respData, respBody);
//
//        System.out.println("wrote the response data into resp body");
//        respBody.close();
//        success = true;

      }
//      if(!success){
//        //if it was bad
//        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
//        exchange.getResponseBody().close();
//      }
    }
    catch (IOException e) {

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
//
//  private String readString(InputStream in) throws IOException {
//    StringBuilder sb = new StringBuilder();
//    BufferedReader br = new BufferedReader(new InputStreamReader(in));
//    String read = "";
//
//    while ((read=br.readLine()) != null) {
//      //System.out.println(read);
//      sb.append(read);
//    }
//
//    br.close();
//    return sb.toString();
//  }

}
