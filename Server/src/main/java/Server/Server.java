package Server;

import Auxiliary.*;
import Commands.*;
import Modules.*;
import Organization.Organization;

import java.io.FileInputStream;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * Class for work with server app
 */
public class Server {
    private static final int PORT = 3030;
    public static void main(String[] args) {
        // Message about start work server
        System.out.println("Server is working...");

        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/lb07";
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.cfg"));
            Connection connection = DriverManager.getConnection(url, properties);
            SQL sql = new SQL();
            sql.setConnection(connection);
            ServerSocketChannel server = ServerSocketChannel.open();
            SocketAddress address = new InetSocketAddress(PORT);
            server.bind(address);
            while (true) {

                SocketChannel socket = server.accept();
                System.out.println("Client connected.");

                new Thread(() -> {
                    try {
                        Vector<Organization> Organization = CreateCollection.create(socket, connection);

                        while (true){
                            if (connection.isClosed())
                                break;
                            if (Authorization.isAuthorizated(socket, connection))
                                break;
                        }


                        while (true){
                            Message messageGet = ReadRequest.getMessage(socket);

                            if (messageGet.getStatus().equals("update")){
                                Message messageSend = new Message(socket);
                                Organization t = Organization.stream().filter(o -> o.getId() == parseInt(messageGet.getMessage())).collect(Vector<Organization>::new, Vector<Organization>::add, Vector<Organization>::addAll).get(0);
                                messageSend.setOrganizationMessage(t);
                                SendResponse.sendMessage(messageSend);
                                continue;
                            }
                            System.out.println(messageGet.print());

                            if (messageGet.getCommand() == null){
                                System.out.println("Client is out");
                                Save save = new Save();
                                save.setOrg(Organization);
                                save.save();
                                break;
                            }
                            messageGet.setOrg(Organization);

                            //4
                            Message messageSend = new Message(socket, messageGet.getCommand().execute());
                            SendResponse.sendMessage(messageSend);
                        }
                        connection.close();
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (BindException ex){
            System.out.println("Try another port");
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error...");
        }
        System.out.println("Server is closed");
    }

    public String get_SHA_512_SecurePassword(String passwordToHash, String salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
