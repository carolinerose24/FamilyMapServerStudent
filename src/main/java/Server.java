import com.sun.net.httpserver.HttpServer;
import handlers.PersonHandler;
import handlers.RegisterHandler;
import services.RegisterService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.FileHandler;

public class Server {


  private static final int MAX_WAITING_CONNECTIONS = 12;
  private HttpServer server;

  private void run(String portNumber) throws IOException {
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
    server.createContext("/user/register", new RegisterHandler());
    //that path is a prefix --> one of these contexts would get both /event and /event/8394839ID (so the handler must check for suffix

    server.createContext("/person", new PersonHandler());



    //need to do this for ALL of the url methods



    server.createContext("/", new FileHandler()); //this needs to be checked LAST

    System.out.println("Starting server");
    server.start();
    System.out.println("Server started");

  }


  /**
   * Takes portNumber (unsigned 16 bit int --> just a random number)
   * @param args port number
   */
  public static void main(String[] args){
    String portNumber = args[0];
    new Server().run(portNumber);
  }
}
