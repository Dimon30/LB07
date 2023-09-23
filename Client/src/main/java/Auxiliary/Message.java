package Auxiliary;

import Commands.*;
import Organization.Organization;

import java.io.*;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private String message;
    private String status = "status";
    private transient SocketChannel socketChannel;
    private String commandArg;
    private Command command;
    private Vector<Organization> org;
    private Organization o;

    private static final Map<String, Command> commandMap = new HashMap<String, Command>();
    static {
        commandMap.put("testCommand", new TestCommand());
        commandMap.put(Help.getName(), new Help());
        commandMap.put(Info.getName(), new Info());
        commandMap.put(Show.getName(), new Show());
        commandMap.put(Add.getName(), new Add());
        commandMap.put(Update.getName(), new Update());
        commandMap.put(Remove.getName(), new Remove());
        commandMap.put(Clear.getName(), new Clear());
        commandMap.put(Execute_script.getName(), new Execute_script());
        commandMap.put(Exit.getName(), new Exit());
        commandMap.put(Reorder.getName(), new Reorder());
        commandMap.put(Sort.getName(), new Sort());
        commandMap.put(History.getName(), new History());
        commandMap.put(FilterByTypeAsc.getName(), new FilterByTypeAsc());
        commandMap.put(PrintByAddressAscending.getName(), new PrintByAddressAscending());
        commandMap.put(PrintByAddressDescending.getName(), new PrintByAddressDescending());
    }

    private SocketChannel socket;
    //private String status;

    public Message(){}
    public Message(SocketChannel socket, String s) {
        this.socketChannel = socket;
        this.message = s;
    }

    public Message(SocketChannel socket) {this.socketChannel = socket;}
    public Message(String message   ) {this.message = message;}

    public Message(SocketChannel socket, String s, String update) {
        this.socketChannel = socket;
        this.message = s;
        this.status = update;
    }

    public String getMessage() {
        return message;
    }
    public boolean validate() {
        String[] split = commandArg.split("\s");
        String command = split.length > 1 ? split[0] : commandArg;
        String[] arg = split.length > 1 ? Arrays.copyOfRange(split, 1, split.length) : new String[0];

        if (!commandMap.containsKey(command))
            return false;

        this.command = commandMap.get(command);
        this.command.setArg(arg);
        if (!this.command.validate())
            return false;
        this.command.setType(command);
        History.memorize(commandArg);
        return true;
    }
    public String execute() {return this.message;}

    public void setMessage(String message) {this.message = message;}
    public Command getCommand() {
        return command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }
    public void create(SocketChannel socket, String command_arg, Vector<Organization> org){this.socketChannel = socket; this.commandArg = command_arg; this.org = org;}
    public void create(SocketChannel socket, Vector<Organization> org){this.socketChannel = socket; this.org = org;}
    public void setCommandArg(String commandArg) {this.commandArg = commandArg;}
    public SocketChannel getSocket() {
        return socketChannel;
    }
    public void setSocket(SocketChannel socket) {
        this.socketChannel = socket;
    }
    public Vector<Organization> getOrg() {
        return org;
    }
    public void setOrg(Vector<Organization> org) {
        this.org = org;
    }

    public void create(SocketChannel socket) {
        this.socketChannel = socket;
    }

    public void setOrganization(Organization organization) {this.command.setOrganization(organization);}

    public String getCommandArg() {return commandArg;}

    public Organization getOranization() {return o;}

    public void setStatus(String status) {this.status = status;}

    public String getStatus() {return status;}

    public String print(){return "Message: " + getMessage() + "\n"
            + "Command Arg:" + getCommandArg() + "\n"
            + "Command: " + getCommand() + "\n"
            + "Statis: " + getStatus() + "\n";}
}
