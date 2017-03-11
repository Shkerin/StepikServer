package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Multi jabber client
 *
 * @author Vladimir Shkerin
 * @since 14.02.2017
 */
public class MultiJabberClient {

    private static final int MAX_THREADS = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);

        while (true) {
            if (JabberClientThread.threadCount() < MAX_THREADS) {
                new JabberClientThread(addr);
            }
            Thread.currentThread().sleep(100);
        }
    }

    static class JabberClientThread extends Thread {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private static int counter = 0;
        private int id = counter++;
        private static int threadcount = 0;

        static int threadCount() {
            return threadcount;
        }

        JabberClientThread(InetAddress addr) {
            System.out.println("Making client " + id);
            threadcount++;
            try {
//                socket = new Socket(addr, MultiJabberServer.PORT);
                socket = new Socket(addr, 5050);
            } catch (IOException e) {
                System.err.println("Socket failed");
                // Если создание сокета провалилось,
                // ничего ненужно чистить.
            }
            try {
                in = new BufferedReader(new InputStreamReader(socket
                        .getInputStream()));
                // Включаем автоматическое выталкивание:
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        socket.getOutputStream())), true);
                start();
            } catch (IOException e) {
                // Сокет должен быть закрыт при любой
                // ошибке, кроме ошибки конструктора сокета:
                try {
                    socket.close();
                } catch (IOException e2) {
                    System.err.println("Socket not closed");
                }
            }
            // В противном случае сокет будет закрыт
            // в методе run() нити.
        }

        public void run() {
            try {
                for (int i = 0; i < 25; i++) {
                    out.println("Client " + id + ": " + i);
//                    String str = in.readLine();
//                    System.out.println(str);
                }
                out.println("Bue.");
                System.out.println("Bue.");
            } finally {
                // Всегда закрывает:
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Socket not closed");
                }
                threadcount--; // Завершаем эту нить
            }
        }
    }

}