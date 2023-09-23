package Commands;

import Organization.Organization;
import java.io.Serial;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

/**
 * Class for working with command: print_field_ascending_postal_address
 */
public final class PrintByAddressAscending extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "print_field_ascending_postal_address";
    private final static String description = ": Print field ascending postal address;";

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
     * Function to print organizations by ascending postal address
     */
    public String execute(){
        Vector<Organization> result = org.stream()
                .sorted(Comparator.comparing(Organization::getAddress))
                .collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
        Show show = new Show();
        show.setOrg(result);
        return new Show().execute();
    }

    public boolean validate(){
        if (arg.length > 0){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }
}
