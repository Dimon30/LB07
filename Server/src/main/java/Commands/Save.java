package Commands;

import Auxiliary.Write_XML;

/**
 * Class for working with command: save
 */
public final class Save extends Command{
    private static final String name = "save";
    private final static String description = ": Save collection in file;";

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
     * Function to save collection in file xml
     */
    public void save(){
        try {
            Write_XML.Write(org.stream(), "Organizations.xml"/*"src/main/resources/Organizations.xml"*/);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in saving file");
        }
    }
}
