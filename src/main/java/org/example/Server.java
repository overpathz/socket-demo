package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8921);
        while (true) {
            Socket accept = serverSocket.accept();
            handleConnection(accept);
        }
    }

    private static void handleConnection(Socket accept) throws IOException {
        try(accept;
            InputStream inputStream = accept.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            OutputStream outputStream = accept.getOutputStream();
            Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream));) {

            String response = reader.readLine();
            System.out.println(response);
            writer.write("response from server!\n");
            writer.flush();
        }
    }
}
