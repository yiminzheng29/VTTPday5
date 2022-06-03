package com.example.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) 
            throws IOException {

        //Create a server socket and listen to a port
        ServerSocket server = new ServerSocket(3000);

        System.out.println("Waiting for connection....");
        Socket sock = server.accept();
        System.out.println("Connection accepted");

        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        String request = dis.readUTF();

        // Perform some operation on the request
        request = request.toUpperCase();

        // Write back the data to the client
        dos.writeUTF(request);

        // close the streams
        is.close();
        os.close();
        
        // close the sockets
        sock.close();

    }
    
}
