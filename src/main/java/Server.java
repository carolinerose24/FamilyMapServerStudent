import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpServer;
import generation.*;
import handlers.*;
import services.RegisterService;

import java.io.IOException;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;

public class Server {


  private static final int MAX_WAITING_CONNECTIONS = 12;
  private HttpServer server;

  private LocationData Locations;
  private FirstNameF fNames;
  private FirstNameM mNames;
  private LastNames lNames;


  private void run(String portNumber) {
    System.out.println("Initializing HTTP server");
    try{
      server = HttpServer.create(
              new InetSocketAddress(Integer.parseInt(portNumber)),
              MAX_WAITING_CONNECTIONS);
    }
    catch(IOException e){
      e.printStackTrace();
      return;
    }

    server.setExecutor(null);
    System.out.println("Creating Contexts");
    //this is creating the handlers
    server.createContext("/user/login", new LoginHandler());
    server.createContext("/user/register", new RegisterHandler());
    //that path is a prefix --> one of these contexts would get both /event and /event/8394839ID (so the handler must check for suffix

    server.createContext("/load", new LoadHandler());
    server.createContext("/fill/", new FillHandler());
    server.createContext("/clear", new ClearHandler());
    server.createContext("/person/", new PersonHandler());
    server.createContext("/event/", new EventHandler());


    //need to do this for ALL of the url methods

    server.createContext("/", new DefaultHandler()); //this needs to be checked LAST

    System.out.println("Starting server");
    server.start();
    System.out.println("Server started");

  }



  /**
   * Takes portNumber (unsigned 16 bit int --> just a random number)
   * @param args port number
   */
  public static void main(String[] args)  {
    String portNumber = args[0];
    new Server().run(portNumber);
  }


}
