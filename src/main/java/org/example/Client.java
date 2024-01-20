package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
       try(Socket socket = new Socket("localhost", 8921);
           BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
           Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

           writer.write("Hello!\n");
           writer.flush();

           String response = reader.readLine();
           System.out.println(response);
       }
    }
}
