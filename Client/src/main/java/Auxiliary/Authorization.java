package Auxiliary;

import Modules.GetResponse;
import Modules.SendRequest;

import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Authorization {
    public static boolean isAuthorizated(SocketChannel socket){
        Message message = new Message(socket);
        Scanner scan = new Scanner(System.in);

        System.out.print("Login: ");
        if (!scan.hasNext()){return false;}
        String login = scan.nextLine();
        message.setMessage(login.replaceAll("\\s+", " ").trim());
        // 1)
        SendRequest.sendMessage(message);
        System.out.print("Password: ");
        if (!scan.hasNext()){return false;}
        String password = scan.nextLine();
        message.setMessage(password);
        // 2)
        SendRequest.sendMessage(message);

        // 3)
        message = GetResponse.getMessage(socket);
        if (message.execute().equals("authorized"))
            return true;
        System.out.println(message.execute());
        return false;
    }
}
