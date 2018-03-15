public class SocketTest {
  private static ServerSocket serverSocket;
  private static Socket clientSocket;
  private static BufferedReader bufferedReader;
  private static String inputLine;
  public static void main(String[] args)
  {
    try {
      serverSocket = new ServerSocket(8000);
      clientSocket = serverSocket.accept();
      bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      while((inputLine = bufferedReader.readLine()) != null)
      System.out.println(inputLine);
    }
    catch(IOException e) {
      System.out.println(e);
    }
  }
}