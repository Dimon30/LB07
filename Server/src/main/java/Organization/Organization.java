/* FILE NAME   : Organization.java
 * PROGRAMMER  : DS6
 * @author     : Sokolov Dmitry
 * LAST UPDATE : 30.04.2023
 * PURPOSE     : Info Organization
 */

package Organization;

import Commands.Add;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Class for working with organization from collection
 */
public class Organization implements Serializable {
    private int id = 30; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Float annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
    private Address postalAddress; //Поле может быть null
    private String lastUpdate;

    /**
     * Constructor of organization
     * @param name name of organization
     * @param x x-coordinates
     * @param y y-coordinates
     * @param creationDate creation date of organization
     * @param annualTurnover annual turnover of organization
     * @param type type of organization
     * @param ad address of organization
     */
    public Organization(String name, long x, double y, LocalDateTime creationDate, Float annualTurnover, OrganizationType type, Address ad){
        this.id = (int) (Math.random() * 999999999 + 1);
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        setCreationDate(creationDate);
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.postalAddress = ad;
        this.lastUpdate = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Constructor of organization
     * @param name name of organization
     * @param x x-coordinates
     * @param y y-coordinates
     * @param creationDate creation date of organization
     * @param annualTurnover annual turnover of organization
     * @param type type of organization
     * @param street street of organization
     * @param zipcode zipcode of organization
     * @param loc_x x coordinate location of organization
     * @param loc_y y coordinate location of organization
     */
    public Organization(String name, String x, String y, String creationDate, String annualTurnover, String type, String street, String zipcode, String loc_x, String loc_y, String town){
        this.id = (int) (Math.random() * 999999999 + 1);
        this.name = name;
        this.coordinates = new Coordinates(Long.parseLong(x), Double.parseDouble(y));
        this.creationDate = LocalDate.parse(creationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay();
        this.annualTurnover = Float.valueOf(annualTurnover);
        this.type = OrganizationType.findTypeByName(type);
        this.postalAddress = new Address(street, zipcode, new Location(Integer.parseInt(loc_x), Long.parseLong(loc_y), town));
        this.lastUpdate = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Constructor of organization
     * @param name name of organization
     * @param x x-coordinate of organization
     * @param y y-coordinate of organization
     * @param annualTurnover annual turnover of organization
     * @param type type of organization
     * @param ad address of organization
     */
    public Organization(String name, long x, double y, Float annualTurnover, OrganizationType type, Address ad){
        this.id = (int) (Math.random() * 999999999 + 1);
        this.name = name;
        this.coordinates = new Coordinates(x, y);
        setCreationDate(LocalDateTime.now());
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.postalAddress = ad;
        this.lastUpdate = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Function to output organization in terminal
     */
    public String print() {
        return " - id = " + this.id + "\n" +
                " - name = " + this.name + "\n" +
                coordinates.print() +
                " - Date = " + this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n" +
                " - Annual Turnover = " + this.annualTurnover + "\n" +
                " - Type = " + this.type + "\n" +
                postalAddress.print();
    }
    /**
     * Function to get name of organization
     * @return name
     */
    public String getName(){return this.name;}
    /**
     * Function to get organization in xml format
     * @return xml
     */
    public String getXML(){
        String start = "\t<Organization>\n";
        String id = getIdinXML();
        String name = getNameinXMl();
        String coordinates = getCoordinatesinXML();
        String creationDate = getCreatonDateinXML();
        String annualTurnover = getAnnualTurnoverinXML();
        String type = getTypeinXML();
        String address = getAddressinXML();
        String lastUpdate = "\t\t<lastUpdate>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "</lastUpdate>\n";
        String end = "\t</Organization>\n";
        return  start + id + name + coordinates + creationDate + annualTurnover + type + address + lastUpdate + end;
    }
    /**
     * Function to get id in xml format
     * @return xml
     */
    private String getIdinXML(){return "\t\t<id>" + String.valueOf(this.id) + "</id>\n";}
    /**
     * Function to get name in xml format
     * @return xml
     */
    private String getNameinXMl(){return "\t\t<name>" + this.name + "</name>\n";}
    /**
     * Function to get coordinates in xml format
     * @return xml
     */
    private String getCoordinatesinXML(){return coordinates.getCoordinatesinXML();}
    /**
     * Function to get creation date in xml format
     * @return xml
     */
    public String getCreatonDateinXML(){return "\t\t<creationDate>" + String.valueOf(this.creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) + "</creationDate>\n";}
    /**
     * Function to get annual turnover in xml format
     * @return xml
     */
    public String getAnnualTurnoverinXML(){return "\t\t<annualTurnover>" + String.valueOf(this.annualTurnover) + "</annualTurnover>\n";}
    /**
     * Function to get type organization in xml format
     * @return xml
     */
    public String getTypeinXML(){return "\t\t<type>" + String.valueOf(this.type) + "</type>\n";}
    /**
     * Function to get address in xml format
     * @return xml
     */
    public String getAddressinXML(){return postalAddress.getAddressinXML();}
    /**
     * Function to set creation date
     * @param creationDate date creation of organization
     */
    public void setCreationDate(LocalDateTime creationDate){
        this.creationDate = creationDate;
    }
    /**
     * Function to set id
     * @param id id of organization
     */
    public void setId(int id){this.id = id;}
    /**
     * Function to set last update
     * @param s date of last update organization
     */
    public void setLastUpdate(String s){this.lastUpdate = s;}
    /**
     * Function to get date of last update
     * @return date last update
     */
    public String getLastUpdate(){return this.lastUpdate;}
    /**
     * Function to set name
     * @param name name of organization
     */
    public void setName(String name) {this.name = name;}
    /**
     * Function to set type
     * @param type type of organization
     */
    public void setType(String type) {this.type = OrganizationType.findTypeByName(type);}
    /**
     * Function to set type of organization
     * @param coordinates
     */
    public void setCoordinates(String coordinates) {
        this.coordinates = new Coordinates(coordinates);
    }
    /**
     * Function to get coordinates in format class Coordinates
     * @return x coordinate in format class Coordinates
     */
    public Coordinates getCoordinates() {return this.coordinates;}
    /**
     * Function to set annual turnover of organization
     * @param annualTurnover annual turnover of organization
     */
    public void setAnnualTurnover(String annualTurnover) {
        this.annualTurnover = Float.valueOf(annualTurnover);
    }
    /**
     * Function to get address of organization
     * @return address
     */
    public String getAddress(){
        return this.postalAddress.getAddress();
    }
    /**
     * Function to get address of organization
     * @return address
     */
    public Address getaddress(){return this.postalAddress;}
    /**
     * Function to set postal address of organization
     * @param postalAddress postal address of organization
     */
    public void setPostalAddress(String postalAddress) {this.postalAddress = new Address(postalAddress, this.postalAddress);}
    /**
     * Function to get id of organization
     * @return id
     */
    public int getId(){return id;}
    /**
     * Function to get type of organization
     * @return type of organization
     */
    public OrganizationType getType() {
        return this.type;
    }
    /**
     * Function to comparing types
     * @param type type of organization
     * @return boolean type compare to type
     */
    public boolean equals(String  type){
        return (this.type.toString().compareTo(type) > 0);
    }
    /**
     * Function to get creationDate of organization
     * @return creation date of organization
     */
    public Date getCreationDate() {return (Date) Date.from(this.creationDate.atZone(ZoneId.systemDefault()).toInstant());}
    /**
     * Function to get annual turnover of organization
     * @return annual turnover of organization
     */
    public float getAnnualTurnover() {return this.annualTurnover;}
}