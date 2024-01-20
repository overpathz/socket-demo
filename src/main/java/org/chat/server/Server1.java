package org.chat.server;

import org.chat.ServerConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server1 {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.SERVER_PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    String message = readMessageFromSocket(clientSocket);
                    printMessage(clientSocket, message);
                }
            }
        }
    }

    public static String readMessageFromSocket(Socket socket) throws IOException {
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }

    public static void printMessage(Socket socket, String message) {
        InetAddress clientAddress = socket.getInetAddress();
        System.out.print(LocalDateTime.now().format(TIME_FORMATTER) + " ");
        System.out.printf("[%s]", clientAddress.getHostAddress());
        System.out.println(" -- " + message);
    }
}