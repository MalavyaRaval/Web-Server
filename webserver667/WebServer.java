package webserver667;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import driver.IStartableServer;
import webserver667.configuration.MimeTypes;
import webserver667.configuration.ServerConfiguration;
import webserver667.requests.RequestHandler;

public class WebServer implements IStartableServer {


  private ServerSocket welcomeSocket;

  @Override
  public void close() throws Exception {

    
    try {
      // Close the server socket to stop accepting new connections
      if (welcomeSocket != null && !welcomeSocket.isClosed()) {
        welcomeSocket.close();
      }
     
    } catch (IOException e) {
      
      e.printStackTrace();
    }

  }

  @Override
  public void start(ServerConfiguration configuration, MimeTypes mimeTypes) {
    try {
      welcomeSocket = new ServerSocket(configuration.getPort());

      while (true) {
        Socket connectionSocket = welcomeSocket.accept();
        Runnable runnable = new RequestHandler(connectionSocket, mimeTypes, configuration.getDocumentRoot());
        (new Thread(runnable)).start();

      }
    } catch (Exception e) {
      // print the line of the code that threw the exception
      System.out.println("Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public void stop() {

    try {
      close(); // Close the server socket and other resources
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
