package Commands;

import Organization.Organization;
import java.io.Serial;
import java.util.Arrays;
import java.util.Vector;

public final class FilterByTypeAsc extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "filter_greater_than_type";
    private final static String description = ": show organizations with type > than given value;";

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
     * Function print organizations which have type greater than given
     */
    public String execute() {
        try {
            String type = arg[0];
            Vector<Organization> result = org.stream().filter(o -> o.equals(type)).collect(Vector<Organization>::new, Vector<Organization>::add,Vector<Organization>::addAll);
            Show show = new Show();
            show.setOrg(result);
            return new Show().execute();
        } catch (Exception e){
            return "You input incorrect data's";
        }
    }

    public boolean validate(){
        if (arg.length == 0) {
            System.out.println("Please input type in format: filter_greater_than_type 'type'\n");
            return false;
        }
        if (arg.length > 1){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }
}

