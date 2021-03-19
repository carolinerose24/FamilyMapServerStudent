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


        //then create a file object and check if the file exists (file.exists())
        //return a 404 if not found
        //if file exists, read the file and write it to exchange's outputStream
        File file = new File(filePath);
        if(file.exists()){

          OutputStream respBody = exchange.getResponseBody();
//          OutputStream respBody =  null;
           // = exchange.getResponseBody();

//          Files.copy(file.toPath(), respBody);
          //need to somehow find a way to write the file into respBody outputstream



          Path path = Paths.get(filePath);
          byte[] data = Files.readAllBytes(path);

          try{
            respBody.write(data);
          } catch (IOException e){
            e.printStackTrace();
          }

//          for(int i = 0; i < data.length; ++i){
//            respBody.write(data[i]);
//          }

//          respBody = exchange.getResponseBody();

















//          OutputStream respBody = null;

//          FileInputStream fileInputStream = null;
//          int a = (int)file.length();
//          byte[] bFile = new byte[(int) file.length()];
//          try
//          {
//            //convert file into array of bytes
//            fileInputStream = new FileInputStream(file);
//            //respBody = new FileOutputStream(file);
////            respBody = new OutputStream();
//            fileInputStream.read(bFile);
//            fileInputStream.close();
//            //respBody.write(bFile);
//
//            for (int i = 0; i < bFile.length; i++)
//            {
////              System.out.print((char) bFile[i]);
//              respBody.write(bFile[i]);
//            }
//            System.out.println("should have read the entire byte file now");
//          }
//          catch (Exception e)
//          {
//            e.printStackTrace();
//          }


//          respBody = exchange.getResponseBody();















//          try{
////            FileInputStream fin=new FileInputStream(file);
////            int i=0;
////            while((i=fin.read())!=-1){
//////              System.out.print((char)i);
////              respBody.write(fin.readAllBytes());
////            }
////            fin.close();
//
//
//            OutputStream outputStream = new FileOutputStream(file);
//
////            while(file.) {
////              file
////              outputStream.write(data);
////            }
////            outputStream.close();
//
//            respBody = outputStream;
//
//
//          }catch(Exception e){System.out.println(e);}
//











          exchange.sendResponseHeaders(HttpsURLConnection.HTTP_OK,0);
          respBody.close();
          success = true;
          //return success file code
          //REALLY NOT SURE IF THIS IS ALL YOU NEED TO DO RIGHT HERE??????????
        } else {
          //return the 404 error
          exchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, 0);
          exchange.getResponseBody().close();
        }
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
