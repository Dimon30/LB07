package Auxiliary;

import Organization.Organization;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

/**
 * Class for write collection in xml file
 */
public class Write_XML {
    /**
     * Function to write collection to xml file
     * @param vec collection of organizations
     * @param filename name of file where will be store collection
     */
    public static void Write(Stream<Organization> vec, String filename){
        try {
            FileOutputStream file = new FileOutputStream(filename);
            file.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Organizations>\n".getBytes());
            vec.forEach(o -> {
                try {
                    file.write(o.getXML().getBytes());
                } catch (IOException e) {System.out.println("Datas don't written");}
            });
            file.write("</Organizations>".getBytes());
            file.close();
        } catch (Exception e) {
            System.out.println("File's not written");
        }
    }
}
