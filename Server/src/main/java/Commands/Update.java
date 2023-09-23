package Commands;

import Organization.*;
import java.util.*;

/**
 * Class for working with command: update
 */
public class Update extends Command{
    private static final String name = "update";
    private final static String description = ": Update value of field for organization from collection;";

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
     * Function to update values of organization in collection by id
     */
    public String execute(){
        try{
            int id = o.getId();
            org = org.stream().filter(o  -> o.getId() != id).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            org.add(o);
        } catch (Exception e){
            return "Error in process updating";
        }
        return "Organization's been successfully updated";
    }
    public boolean validate(){
        if (o == null){
            validateInfo = "Don't find organization by this id(";
            return false;
        }
        if (arg.length == 0){
            System.out.println("Please input command in format: update 'id'");
            return false;
        }
        if (arg.length > 1){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        //setOrganization(processingUpdate(new Scanner(System.in)));
        return true;
    }

}
