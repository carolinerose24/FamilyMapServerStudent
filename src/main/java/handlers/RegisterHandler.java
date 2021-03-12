package handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

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
        Headers reqHeaders = exchange.getRequestHeaders();

        //none of the post methods require an auth key
        InputStream reqBody = exchange.getRequestBody();
        String reqData = readString(reqBody);


        //now go to the db,
        String respData = ""; //need to get this from somewhere, i think it should be json?
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream respBody = exchange.getResponseBody();

        //write the json string to the output stream (small method below)
        writeString(respData, respBody);

        respBody.close();
        success = true;


      }

      if(!success){
        //if it was bad
        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
        exchange.getResponseBody().close();
      }
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

  private String readString(InputStream in) throws IOException {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = new BufferedReader(new InputStreamReader(in));
    String read = "";

    while ((read=br.readLine()) != null) {
      //System.out.println(read);
      sb.append(read);
    }

    br.close();
    return sb.toString();
  }

}
