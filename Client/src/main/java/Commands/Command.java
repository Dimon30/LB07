package Commands;

import Organization.Organization;
import java.io.*;
import java.util.Vector;

public class Command implements Serializable, Validator {
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "command";
    private String type = name;
    protected String[] arg;
    protected Vector<Organization> org;
    protected Organization o;

    public String execute(){return "I'm in command";}

    public static String getName(){return name;}

    public String getCommandArg(){return name + arg;}

    public Vector<Organization> getOrg(){return org;}
    public void setOrg(Vector<Organization> organizations) {this.org = organizations;}
    public void setArg(String[] arg){
        this.arg = arg;
    }
    public String[] getArg() {return arg;}
    public void setOrganization(Organization or){this.o = or;}

    public String getType() {return type;}

    public void setType(String command) {this.type = command;}

    @Override
    public boolean validate() {return true;}
}
