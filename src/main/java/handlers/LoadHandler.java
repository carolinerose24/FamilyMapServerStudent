package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import generation.Deserialize;
import generation.Serialize;
import request.LoadRequest;
import request.RegisterRequest;
import result.LoadResult;
import result.RegisterResult;
import services.LoadService;

import java.io.*;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange exchange) throws IOException {

    try{
      if(exchange.getRequestMethod().toLowerCase().equals("post")){

        LoadService loadSer = new LoadService();
        InputStream reqBody = exchange.getRequestBody();
        Reader json =  new InputStreamReader(reqBody);
        LoadRequest loadRequest = Serialize.SerializeLoadRequest(json);
        LoadResult loadResult = loadSer.loadData(loadRequest);

        if(loadResult.isSuccess()){
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        } else{
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        }
        String respData = Deserialize.toJsonDeserialize(loadResult);
        OutputStream respBody = exchange.getResponseBody();
        writeString(respData, respBody);
        respBody.close();
      }
    } catch (IOException e){
      exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
      LoadResult loadRes = new LoadResult("Error: Internal service error", false);
      String respData = Deserialize.toJsonDeserialize(loadRes);
      OutputStream respBody = exchange.getResponseBody();
      writeString(respData, respBody);
      respBody.close();
      e.printStackTrace();
    }
  }

  private void writeString(String str, OutputStream os) throws IOException {
    OutputStreamWriter sw = new OutputStreamWriter(os);
    sw.write(str);
    sw.flush();
  }
}
