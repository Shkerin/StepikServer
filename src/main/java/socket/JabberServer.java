package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket server
 *
 * @author Vladimir Shkerin
 * @since 14.02.2017
 */
public class JabberServer {
    // Выбираем порт вне пределов 1-1024:
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket s = new ServerSocket(PORT)) {
            System.out.println("Started: " + s);

            // Блокирует до тех пор, пока не возникнет соединение:
            try (Socket socket = s.accept()) {

                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                // Вывод автоматически выталкивается из буфера PrintWriter'ом
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);

                while (true) {
                    String str = in.readLine();
                    if (str.equals("END"))
                        break;
                    System.out.println("Echoing: " + str);
                    out.println(str);
                }

            } finally {
                System.out.println("closing...");
            }

        }
    }
}
