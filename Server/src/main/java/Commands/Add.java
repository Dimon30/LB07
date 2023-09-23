package Commands;

import Auxiliary.SQL;
import Organization.*;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

/**
 * Class for actions with command: add
 */
public final class Add extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private final static String name = "add";
    private final static String description = ": Add new organization to collection;";

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
     * Function add new organizations to collection
     */
    public String execute(){
        try {
            org.add(o);
            org = org.stream()
                    .sorted(Comparator.comparing((Organization o) -> o.getName()))
                    .collect(Vector<Organization>::new, Vector<Organization>::add, Vector<Organization>::addAll);
            SQL.executeAdd(o);
        }catch (Exception e){
            return "Command::Add: 'Oops... Something going wrong. Probably you input incorrect value.'";
        }
        return "Organization was added";
    }

    public boolean validate(){
        if (arg.length > 0){
            validateInfo = "Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n";
            return false;
        }
        return true;
    }
}
