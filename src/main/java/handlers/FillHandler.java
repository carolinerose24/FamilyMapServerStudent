package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import generation.Deserialize;
import request.FillRequest;
import result.EventResult;
import result.FillResult;
import services.FillService;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try{

      if(exchange.getRequestMethod().toLowerCase().equals("post")){
        FillService fSer = new FillService();
        //no auth token

        String requestUrl = exchange.getRequestURI().toString();
        requestUrl = requestUrl.substring(6); //take out the /fill/
        // /fill/username/optionalnumber

        FillRequest fReq;
        FillResult fRes;
        String username = "";
        if(requestUrl.contains("/")){
          int indexOfSlash = requestUrl.indexOf('/');
          username = requestUrl.substring(0, indexOfSlash);
          String numGen = requestUrl.substring(++indexOfSlash);
          int gen = 0;

          try{
            gen = Integer.parseInt(numGen);
            if(gen >= 0){
              fReq = new FillRequest(username, gen);
            } else {
              fReq = null;
            }
          } catch (NumberFormatException e){
            fReq = null;
          }

          //if this is entered wrongly, would throw exception?

          //there is the number, gotta separate it
        } else {
          username = requestUrl;
          fReq = new FillRequest(username);
        }

        if(fReq == null){
          fRes = new FillResult("Error: Invalid username or generations parameter", false);
        } else {
          fRes = fSer.fillGenerations(fReq);
        }


        if(fRes.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else if (!fRes.getMessage().equals("\"Error: Internal service error\"")){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        } else {
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
        }

        String respData = Deserialize.toJsonDeserialize(fRes);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respData, respBody);
        respBody.close();

      }
    } catch (IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0); //this is the internal error
      FillResult fRes = new FillResult("Error: Internal service error", false);
      String respData = Deserialize.toJsonDeserialize(fRes);
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
