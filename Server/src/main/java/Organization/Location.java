/* FILE NAME   : Location.java
 * PROGRAMMER  : DS6
 * @author     : Sokolov Dmitry
 * LAST UPDATE : 30.04.2023
 * PURPOSE     : Location Organization
 */

package Organization;

import java.io.Serializable;

/**
 * Class for working with field of organization: location
 */
public class Location implements Serializable {
    private int x;
    private long y;
    private String town; //Поле не может быть null

    /**
     * Constructor of field: Location
     * @param x x-coordinate of town of organization
     * @param y y-coordinate of town of organization
     * @param town town of organization
     */
    public Location(int x, long y, String town){
        this.x = x;
        this.y = y;
        this.town = town;
    }

    /**
     * Constructor of field: location, for command update
     * @param postalAddress postal address of organization in format string
     */
    public Location(String postalAddress) {
        if (postalAddress.contains(", "))
        {
            try {
                if (!postalAddress.split(", ")[2].equals("-"))
                    this.x = Integer.parseInt(postalAddress.split(", ")[2]);
                if (!postalAddress.split(", ")[2].equals("- "))
                    this.x = Integer.parseInt(postalAddress.split(", ")[2]);
                if (!postalAddress.split(", ")[3].equals("-"))
                    this.y = Long.parseLong(postalAddress.split(", ")[3]);
                if (!postalAddress.split(", ")[3].equals("- "))
                    this.y = Long.parseLong(postalAddress.split(", ")[3]);
                if (!postalAddress.split(", ")[4].equals("-"))
                    this.town = postalAddress.split(", ")[4];
                if (!postalAddress.split(", ")[4].equals("- "))
                    this.town = postalAddress.split(", ")[4];
            } catch (IndexOutOfBoundsException e){
                System.out.println("Incorrect data");
            }
        }
    }

    /**
     * Function to output location in terminal
     */
    public String print(){
        return " - Location 'x' = " + this.x + "\n" +
        " - Location 'y' = " + this.y + "\n" +
        " - Town = " + this.town + "\n";
    }

    /**
     * Function to get location in string format xml
     * @return xml
     */
    public String getLocationinXML(){
        String start = "\t\t\t<Location>\n";
        String x = "\t\t\t\t<x>" +  String.valueOf(this.x) + "</x>\n";
        String y = "\t\t\t\t<y>" +  String.valueOf(this.y) + "</y>\n";
        String town = "\t\t\t\t<town>" +  String.valueOf(this.town) + "</town>\n";
        String end = "\t\t\t</Location>\n";
        return start + x + y + town + end;
    }

    /**
     * Function to get town in format string
     * @return town in format string
     */
    public String getTown() {
        return town;
    }
    /**
     * Function to get coordinate x
     * @return x
     */
    public int getLocX() {return x;}
    /**
     * Function to get coordinate y
     * @return y
     */
    public long getLocY() {return y;}
}