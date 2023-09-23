package Commands;

import java.io.Serial;
import java.util.Arrays;

public final class Show extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "show";
    private final static String description = ": Show all organizations in collection;";

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

    public String execute() {
        if (org.stream().count() == 0) {
            return "Collection is empty\n";
        }
        final String[] showReturn = {"\n"};
        final Integer[] i = {1};
        try{
            org.forEach(o -> {
                showReturn[0] += "Organization #" + i[0] + ":\n";
                i[0] += 1;
                showReturn[0] += o.print();
                showReturn[0] += "------------------------------\n";
            });
        } catch(Exception e) {
            showReturn[0] += "Show::execute: 'Can't show collection'";
        }
        return showReturn[0];
    }

    public boolean validate(){
        if (arg.length > 0){
            validateInfo = "Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n";
            return false;
        }
        return true;
    }
}
