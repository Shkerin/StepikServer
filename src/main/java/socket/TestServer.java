package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Test server for
 *
 * @author Vladimir Shkerin
 * @since 14.02.2017
 */
public class TestServer extends Thread {

    static final int PORT = 5050;
    static final boolean ENABLED_LOG = false;

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public TestServer() {
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            socket = serverSocket.accept();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch (IOException e) {
            if (ENABLED_LOG) e.printStackTrace();
        }

        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("Bue.")) {
                    if (ENABLED_LOG) System.out.println("Bue.");
                    break;
                }
                out.print(str);
                if (ENABLED_LOG) System.out.println(str);
            }
        } catch (IOException e) {
            if (ENABLED_LOG) System.err.println("IOException");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                if (ENABLED_LOG) System.err.println("Socket not closed.");
            }
        }
    }

}
