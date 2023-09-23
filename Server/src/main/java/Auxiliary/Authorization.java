package Auxiliary;

import Modules.ReadRequest;
import Modules.SendResponse;

import java.beans.Statement;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

public class Authorization {
    public static boolean isAuthorizated(SocketChannel socket, Connection connection){
        try {

            java.sql.Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            // 1)
            Message messageLogin = ReadRequest.getMessage(socket);
            // 2)
            Message messagePass = ReadRequest.getMessage(socket);
            String login = messageLogin.execute();
            String password = messagePass.execute();
            ResultSet res = statement.executeQuery("select * from users where login = '" + login + "';");
            PreparedStatement ps = connection.prepareStatement("insert into users values (?, ?, ?);");
            Map<String, ArrayList<String>> data = GetData.getData(res);
            res.close();
            statement.close();

            if (data.get("login") == null){
                ps.setInt(1, (int)(Math.random() * 1000000 + 1));
                ps.setString(2, login);
                ps.setString(3, password);
                ps.executeUpdate();
                System.out.println("Added new user");

                // 3)
                Message message = new Message(socket, "authorized");
                SendResponse.sendMessage(message);
                return true;
            }
            if (data.get("password").get(0).equals(password)){
                Message message = new Message(socket, "authorized");
                SendResponse.sendMessage(message);
                return true;
            }

            Message message = new Message(socket, "Incorrect password");
            SendResponse.sendMessage(message);

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
