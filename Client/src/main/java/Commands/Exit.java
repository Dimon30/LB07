package Commands;

import java.io.Serial;
import java.util.Arrays;

/**
 * Class for actions with command: exit
 */
public final class Exit extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "exit";
    private static final String description = ": exit;";

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
     * Function to abort program
     */
    public String execute(){System.exit(30);return null;}

    public boolean validate(){
        if (arg.length > 0){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }

}
