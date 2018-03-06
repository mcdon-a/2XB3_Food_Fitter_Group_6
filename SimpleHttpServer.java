/** source:
 * 
 * http://www.rgagnon.com/javadetails/java-have-a-simple-http-server.html
 * 
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

public class SimpleHttpServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/info", new InfoHandler());
    server.createContext("/get", new GetHandler());
    server.createContext("/jsonFile.json", new JSONHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class InfoHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      String response = "Use /get to download a file";
      t.sendResponseHeaders(200, response.length());
      OutputStream os = t.getResponseBody();
      os.write(response.getBytes());
      os.close();
    }
  }
  
  static class JSONHandler implements HttpHandler{
    public void handle(HttpExchange t) throws IOException {
      String json = "{\"agent 1\":{\"port\":\"12345\",\"ip\":\"192.67.4.1\",\"neighbours\":{\"agent 2\":{\"port\":\"12345\",\"ip\":\"192.67.4.2\"}},\"measurements\":{\"agent 3\":{\"port\":\"12346\",\"ip\":\"192.67.4.1\"},\"power\":\"7KW\",\"voltage\":\"4.2V\"}}}";
                String response = json.toString();
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
    }
  }
  
  static class GetHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {

      // add the required response header for a PDF file
      Headers h = t.getResponseHeaders();
      h.add("Content-Type", "application/txt");

      // a PDF (you provide your own!)
      File file = new File ("1-rec-and-ind5.pdf");
      byte [] bytearray  = new byte [(int)file.length()];
      FileInputStream fis = new FileInputStream(file);
      BufferedInputStream bis = new BufferedInputStream(fis);
      bis.read(bytearray, 0, bytearray.length);

      // ok, we are ready to send the response.
      t.sendResponseHeaders(200, file.length());
      OutputStream os = t.getResponseBody();
      os.write(bytearray,0,bytearray.length);
      os.close();
    }
  }
}