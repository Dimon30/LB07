package Commands;

import Organization.Organization;
import java.io.Serial;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

/**
 * Class for working with command: reorder
 */
public final class Reorder extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "reorder";
    private final static String description = ": Reverse sort collection;";

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
     * Function to reorder collection
     */
    public String execute(){
        org = org
                .stream()
                .sorted(Comparator.comparing(Organization::getName).reversed())
                .collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
        return "Collection reversed.";
    }

    public boolean validate(){
        if (arg.length > 0){
            validateInfo = "Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n";
            return false;
        }
        return true;
    }
}
