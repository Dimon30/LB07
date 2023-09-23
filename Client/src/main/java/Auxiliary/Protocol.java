package Auxiliary;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Protocol {
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

    public static void sendMessage(Message message) {

    }



    /*private Message processing(Data data, SocketChannel socket) throws Exception {
        return listener.commands.get(data.commandType).execute(data, listener);
    }*/
}
