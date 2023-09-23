package Commands;

import java.io.Serial;
import java.util.Arrays;

/**
 * Class for actions with command: clear
 */
public final class Clear extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "clear";
    private final static String description = ": Clear collection;";

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
     * Function clear collection
     */
    public String execute(){
        try {
            org.clear();
            return "Now the collection is empty.\n";
        } catch (Exception e) {
            return "Error cleaning..\n";
        }
    }

    public boolean validate(){
        if (arg.length > 0) {
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }
}
