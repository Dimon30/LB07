package Modules;

import Auxiliary.Message;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ReadRequest {
    public static String readRequest(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String message = reader.readLine();
            return message;
        } catch (Exception e) {
            System.out.println("No connection to client");
        }
        return "client_out";
    }
    public static Message getMessage(SocketChannel socket) {
        byte[] data = new byte[303030];
        try{
            socket.read(ByteBuffer.wrap(data));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Message message = (Message) objectInputStream.readObject();
            return message;

        }catch (Exception e) {
            return new Message();
        }
    }
}
