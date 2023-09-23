package Commands;

import Organization.Organization;
import java.io.Serial;
import java.util.Arrays;
import java.util.Vector;
import static java.lang.Integer.parseInt;

/**
 * Class to working with command: remove_by_id
 */
public final class Remove extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "remove_by_id";
    private final static String description = ": Remove organization by id;";

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
     * Function to delete organization by id
     */
    public String execute(){
        try {
            int id = parseInt(arg[0]);
            org = org.stream().filter(o  -> o.getId() != id).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            return "Organization by id '" + arg[0] + "' was successfully deleted" + "\n";
        } catch (Exception e) {
            return "Sorry...\nDon't find organization by this id(" + "\n";
        }
    }

    public boolean validate(){
        if (arg.length == 0) {
            validateInfo = "Please input command in format: remove_by_id 'id'" + "\n";
            return false;
        }
        if (arg.length > 1){
            validateInfo = "Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n";
            return false;
        }
        return true;
    }
}
