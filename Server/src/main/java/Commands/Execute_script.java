package Commands;


import Auxiliary.Message;
import Organization.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for actions with command: execute_script
 */
public final class Execute_script extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "execute_script";
    private final static String description = ": Load commands from file;";
    private ArrayList<Message> Messages = new ArrayList<Message>();


    /**
     * Function to get name of command
     * @return name of command
     */
    public static String getName(){return name;}
    /**
     * Function to get description of command
     * @return description of command
     */
    public static String getDescription(){return description;}

    /**
     * Function take file and execute commands
     */
    public String execute(){
        StringBuilder executeReturn = new StringBuilder();
        try {
            for (Message message : Messages){
                if(!message.validate()) {
                    executeReturn.append("'")
                            .append(message.getCommandArg())
                            .append("'\n");
                    if (!message.getCommand().validate())
                            executeReturn
                                    .append(message.getCommand().validateInfo())
                                    .append("\n");
                    continue;
                }
                message.setOrg(org);
                executeReturn
                        .append("'")
                        .append(message.getCommandArg())
                        .append("'\n")
                        .append(message.getCommand().execute())
                        .append("\n");
                History.memorize(message.getCommandArg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Execute_script::execute: 'Something is going wrong'";
        }
        return executeReturn.toString();
    }
    public boolean validate(){
        if (arg.length != 1) {
            validateInfo = "Please input filename in format: 'execute_script filename'\n";
            return false;
        }
        return true;
    }

}
