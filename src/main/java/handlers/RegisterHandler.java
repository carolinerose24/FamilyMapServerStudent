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


        InputStream reqBody = exchange.getRequestBody();
        Reader json =  new InputStreamReader(reqBody);
        RegisterRequest regRequest = Serialize.SerializeRegisterRequest(json);
        RegisterResult regResult = regService.registerUser(regRequest);

        if(regResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          success = true;
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        String respData = Deserialize.toJsonDeserialize(regResult);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respData, respBody);
        respBody.close();
      }
    }
    catch (IOException e) { //i think this is what should be in here? it doesn't throw any errors for unhandled exceptions
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      //is there were we send in a result that says internal service error and false?
      RegisterResult regRes = new RegisterResult("Internal service error", false);
      String respData = Deserialize.toJsonDeserialize(regRes);
      OutputStream respBody = exchange.getResponseBody();
      writeString(respData, respBody);
      respBody.close();
//      exchange.getResponseBody().close();
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
