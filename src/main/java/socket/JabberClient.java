package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Socket client
 *
 * @author Vladimir Shkerin
 * @since 14.02.2017
 */
public class JabberClient {

    public static void main(String[] args) throws IOException {
        InetAddress addr = InetAddress.getByName(null);
        System.out.println("addr = " + addr);

        try (Socket socket = new Socket(addr, JabberServer.PORT)) {
            System.out.println("socket = " + socket);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            // Вывод автоматически Output быталкивается PrintWriter'ом.
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);

            for (int i = 0; i < 10; i++) {
                out.println("howdy " + i);
                String str = in.readLine();
                System.out.println(str);
            }

            out.println("END");
        } finally {
            System.out.println("closing...");
        }
    }

}