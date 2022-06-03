package vttpexercise.day5.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

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
        String[] items = request.split(" ");

        int x = Integer.parseInt(items[0]);
        int y = Integer.parseInt(items[2]);

        if (items[1].equals("+")) {
            int add = x + y;
            dos.writeUTF(Integer.toString(add));
        }

        if (items[1].equals("/")) {
            float divide = Float.parseFloat(items[0]) / y;
            DecimalFormat df = new DecimalFormat("#.##");
            dos.writeUTF(Float.toString(divide));
        }

        if (items[1].equals("*")) {
            int mul = x * y;
            dos.writeUTF(Integer.toString(mul));
        }

        if (items[1].equals("-")) {
            int sub = x - y;
            dos.writeUTF(Integer.toString(sub));
        }


        // close the streams
        is.close();
        os.close();
        
        // close the sockets
        sock.close();

    }
    
}
