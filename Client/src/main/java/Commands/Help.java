package Commands;

import java.io.Serial;
import java.util.Arrays;

/**
 * Class for work with command: help
 */
public final class Help extends Command{

    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "help";
    private static final String description = ": Output all commands;";

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
     * Function print all command
     */
    public String execute(){
        return "-" + getName() + getDescription() + "\n" +
                "-" + Info.getName() + Info.getDescription() + "\n" +
                "-" + Show.getName() + Show.getDescription() + "\n" +
                "-" + Add.getName() + Add.getDescription()  + "\n" +
                "-" + Update.getName() + Update.getDescription() + "\n" +
                "-" + Remove.getName() + Remove.getDescription() + "\n" +
                "-" + Clear.getName() + Clear.getDescription() + "\n" +
                "-" + Execute_script.getName() + Execute_script.getDescription() + "\n" +
                "-" + Exit.getName() + Exit.getDescription() + "\n" +
                "-" + Reorder.getName() + Reorder.getDescription() + "\n" +
                "-" + Sort.getName() + Sort.getDescription() + "\n" +
                "-" + History.getName() + History.getDescription() + "\n" +
                "-" + FilterByTypeAsc.getName() + FilterByTypeAsc.getDescription() + "\n" +
                "-" + PrintByAddressAscending.getName() + PrintByAddressAscending.getDescription() + "\n" +
                "-" + PrintByAddressDescending.getName() + PrintByAddressDescending.getDescription();
    }

    public boolean validate(){
        if (arg.length > 0){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }

}
