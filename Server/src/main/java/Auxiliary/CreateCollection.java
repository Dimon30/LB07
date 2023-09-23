package Auxiliary;

import Commands.Command;
import Modules.ReadRequest;
import Organization.Organization;

import java.beans.Statement;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CreateCollection {
    public static Vector<Organization> create(SocketChannel socket, Connection connection){
        try {
            java.sql.Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet table = statement.executeQuery("select * from organizations");
            Vector<Organization> organizations = new Vector<Organization>();
            while (table.next()) {
                Map<String, String> org = new HashMap<String, String>();
                for (int i = 1; i <= table.getMetaData().getColumnCount(); i++ ) {
                    org.put(table.getMetaData().getColumnName(i), table.getString(i));
                }
                Organization t = new Organization(
                        org.get("name"),
                        org.get("coord_x"),
                        org.get("coord_y"),
                        org.get("creationdate"),
                        org.get("annualturnover"),
                        org.get("type"),
                        org.get("street"),
                        org.get("zipcode"),
                        org.get("loc_x"),
                        org.get("loc_y"),
                        org.get("town"));
                t.setId(Integer.parseInt(org.get("id")));
                organizations.add(t);
            }
            table.close();
            statement.close();
            return organizations;
        } catch (Exception e){
            e.printStackTrace();
            return new Vector<Organization>(0);
        }
    }
}
