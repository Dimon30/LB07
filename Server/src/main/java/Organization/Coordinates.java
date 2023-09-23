/* FILE NAME   : Coordinates.java
 * PROGRAMMER  : DS6
 * @author     : Sokolov Dmitry
 * LAST UPDATE : 30.04.2023
 * PURPOSE     : Coordinates Organization
 */

package Organization;

import java.io.Serializable;

/**
 * Class to working with field of organization: Coordinates
 */
public class Coordinates implements Serializable {
    private long x; //Максимальное значение поля: 890
    private double y;

    public Coordinates(long x, double y){
        if (x >= 890)
            this.x = 890;
        else
            this.x = x;
        this.y = y;
    }

    /**
     * Constructor of field: coordinates
     * @param coord coordinates in format string
     */
    public Coordinates(String coord){
        //String[] split = Arrays.stream(coord.split(Pattern.quote("[,\s]\s*"))).map(String::trim).toArray(String[]::new);
        String[] split = coord.split(", ");
        try {
            this.x = Long.parseLong(split[0]);
            this.y = Double.parseDouble(split[1]);
        }catch (Exception e){
            System.out.println(coord);
            for (String s : split)
                System.out.println(s);
            System.out.println("Sorry. You input incorrect datas");
        }
    }

    /**
     * Function to output in terminal field: coordinates
     */
    public String print(){
        return " - Coordinates 'x' = " + this.x + "\n" +
                " - Coordinates 'y' = " + this.y + "\n";
    }

    /**
     * Function to get coordinates in format xml
     * @return xml coordinates in format string-xml
     */
    public String getCoordinatesinXML(){
        String start = "\t\t<Coordinates>\n";
        String x = "\t\t\t<x>" + String.valueOf(this.x) + "</x>\n";
        String y = "\t\t\t<y>" + String.valueOf(this.y) + "</y>\n";
        String end = "\t\t</Coordinates>\n";
        return start + x + y + end;
    }

    /**
     * Function to get 'x' coordinate in format long
     * @return x coordinate in format long
     */
    public long getX() {return this.x;}
    /**
     * Function to get 'y' coordinate in format double
     * @return y coordinate in format double
     */
    public double getY() {return this.y;}
}