package Auxiliary;

import Organization.Organization;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SQL {
    private static Connection connection;

    public SQL(){}
    public static void executeAdd(Organization org){
        try {
            PreparedStatement ps = connection.prepareStatement("insert into organizations values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, org.getId());
            ps.setString(2, org.getName());
            ps.setLong(3, org.getCoordinates().getX());
            ps.setDouble(4, org.getCoordinates().getY());
            ps.setDate(5, org.getCreationDate());
            ps.setFloat(6, org.getAnnualTurnover());
            ps.setString(7, org.getType().name());
            ps.setString(8, org.getaddress().getStreet());
            ps.setString(9, org.getaddress().getZipcode());
            ps.setInt(10, org.getaddress().getLocation().getLocX());
            ps.setLong(11, org.getaddress().getLocation().getLocY());
            ps.setString(12, org.getaddress().getLocation().getTown());
            ps.execute();
            ps.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
