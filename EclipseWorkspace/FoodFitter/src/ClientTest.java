import java.net.Socket;
import java.io.PrintWriter;
public class ClientTest {
    private static Socket socket;
    private static PrintWriter printWriter;
    public static void main(String[] args) {
        try {
            socket = new Socket("localhost",8000);
            printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("Hello world");
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
