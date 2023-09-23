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
                if(!message.validate())
                    continue;
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
            return "Execute_script::execute: 'Something is going wrong'";
        }
        return executeReturn.toString();
    }
    private void processingFile(){
        String filename = arg[0];
        File file = new File(filename);
        StringBuilder str = new StringBuilder();
        String[] lines;
        try (Scanner reader = new Scanner(new FileReader(file.getAbsolutePath()))) {
            while (reader.hasNextLine()) {
                str.append(reader.nextLine()).append("\n");
            }
            lines = str.toString().split("\n");
            for (String command_arg : lines) {
                Message message = new Message();
                message.setCommandArg(command_arg);
                if (command_arg.equals("add"))
                    message.setOrganization(Add.processingAdd(new Scanner(System.in)));
                if (command_arg.split("s")[0].equals("update")) {
                    System.out.println("To execution command 'update', you need input this command in terminal");
                    continue;
                }

                Messages.add(message);
            }
        } catch (FileNotFoundException e){
            System.out.println("Sorry, I don't find this file: '" + filename + "'");
        } catch (Exception e) {
            System.out.println("Execute_script::execute: 'Something is going wrong'");
        }
    }
    public boolean validate(){
        if (arg.length != 1) {
            System.out.println("Please input filename in format: 'execute_script filename'\n");
            return false;
        }
        processingFile();
        return true;
    }

}
