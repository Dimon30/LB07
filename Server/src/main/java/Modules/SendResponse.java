package Modules;

import Auxiliary.Message;
import Commands.Command;
import Organization.Organization;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.NoSuchElementException;
import java.util.Vector;

public class SendResponse {

    public static void sendMessage(Message message) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(message);
            objectOutputStream.close();
            message.getSocket().write(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error send Message");
        }
    }
    public static void sendResponse(Socket socket, String response){
        try{
            PrintWriter printWriter = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()
                    )
            );
            printWriter.println(response);
            printWriter.flush();
            //System.out.println("Message successful send");
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public static void sendOrganization(Socket socket, Vector<Organization> org, int id) {
        Organization t = null;
        try{
            t = org.stream().filter(o -> o.getId() == id).findFirst().orElseThrow();
        } catch (NoSuchElementException e){
            sendResponse(socket, "Sorry...\nDon't find organization by this id(\n");
        } catch (Exception e){
            sendResponse(socket, "Something is going wrong.\n");
        }
        try {
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(t);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}