package Commands;

import java.io.Serial;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Class for work with command: history
 */
public final class History extends Command{
    @Serial
    private static final long serialVersionUID = 14130070678758251L;
    private static final String name = "history";
    private final static String description = ": Show 8 last commands;";
    private static final Deque<String> history = new ArrayDeque<>(8);


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

    public static void memorize(String command) {
        history.offer(command);

        if (history.size() > 8) {
            history.pop();
        }
    }

    /**
     * Function print last 8 commands which user inputted
     */
    public String execute() {
        String[] historyReturn = {""};
        history.forEach(h -> historyReturn[0] += h);
        return historyReturn[0];
    }

    public boolean validate(){
        if (arg.length > 0){
            System.out.println("Incorrect data's: " + getName() + " " + Arrays.toString(arg) + "\n");
            return false;
        }
        return true;
    }
}
