package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import generation.Deserialize;
import generation.Serialize;
import request.LoginRequest;
import result.LoginResult;
import services.LoginService;

import java.io.*;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    boolean success = false;

    try{
      if(exchange.getRequestMethod().toLowerCase().equals("post")){
        LoginService loginService = new LoginService();


        InputStream reqBody = exchange.getRequestBody();
        Reader json = new InputStreamReader(reqBody);
        LoginRequest loginReq =Serialize.SerializeLoginRequest(json);
        LoginResult loginRes = loginService.userLogin(loginReq);

        if(loginRes.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
          success = true;
        } else{
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        String respData = Deserialize.toJsonDeserialize(loginRes);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respData, respBody);
        respBody.close();

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
