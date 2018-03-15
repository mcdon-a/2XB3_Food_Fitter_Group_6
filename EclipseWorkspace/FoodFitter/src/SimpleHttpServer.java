/** source:
 *
 * http://www.rgagnon.com/javadetails/java-have-a-simple-http-server.html
 *
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.Headers;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SimpleHttpServer {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
    server.createContext("/info", new InfoHandler());
    server.createContext("/get", new GetHandler());
    server.createContext("/posthandler", new postHandler());
    server.createContext("/jsonFile.json", new JSONHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
  }

  static class postHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
      InputStream input = t.getRequestBody();
      Headers requestHeaders = t.getRequestHeaders();
      int contentLength = Integer.parseInt(requestHeaders.getFirst("Content-length"));
      byte[] data = new byte[contentLength];
      int length = input.read(data);
      OutputStream os = t.getResponseBody();
      os.write(data);
      os.close();
    }
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
   StringBuilder response = new StringBuilder();
      Map <String,String>parms = SimpleHttpServer2.queryToMap(httpExchange.getRequestURI().getQuery());
      response.append("<html><body>");
      response.append("hello : " + parms.get("hello") + "<br/>");
      response.append("foo : " + parms.get("foo") + "<br/>");
      response.append("</body></html>");
      SimpleHttpServer2.writeResponse(httpExchange, response.toString());
  }
}

