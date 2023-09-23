package Commands;

import Organization.Organization;
import java.io.Serial;
import java.util.Arrays;

/**
 * Class for work with command: info
 */
public final class Info extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "info";
    private static final String description = ": About collection";

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
     * Function print information about collection
     */
    public String execute() {
        if (org.isEmpty())
            return "Collection is empty";
        Organization date = (Organization)(org.stream().findFirst().orElseThrow());
        int size = (int)org.stream().count();
        String infoReturn = "";
        infoReturn += "Date of initialization collection: " + date.getLastUpdate() + "\n";
        infoReturn += "Size of collection: " + size;
        return infoReturn;
    }

    public boolean validate(){
        if (arg.length > 0){
            validateInfo = "Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n";
            return false;
        }
        return true;
    }
}
