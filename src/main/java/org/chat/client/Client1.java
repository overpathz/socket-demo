package org.chat.client;

import org.chat.ServerConfig;

import java.io.*;
import java.net.Socket;

public class Client1 {
    // 127.0.0.1 - localhost

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = openConsoleReader()) {
            String message = readMessage(reader);

            while (!message.equals("q")) {
                try (Socket socket = new Socket(ServerConfig.SERVER_HOST, ServerConfig.SERVER_PORT);
                     Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {
                    writer.write(message);
                    writer.flush();
                }
                message = readMessage(reader);
            }
        }
    }

    public static BufferedReader openConsoleReader() {
        InputStreamReader consoleInputStream = new InputStreamReader(System.in);
        return new BufferedReader(consoleInputStream);
    }

    public static String readMessage(BufferedReader reader) throws IOException {
        System.out.print("Enter message (q to quit): ");
        return reader.readLine();
    }
}
