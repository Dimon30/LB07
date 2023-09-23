/* FILE NAME   : Address.java
 * PROGRAMMER  : DS6
 * @author     : Sokolov Dmitry
 * LAST UPDATE : 30.04.2023
 * PURPOSE     : Address Organization
 */

package Organization;

import java.io.Serializable;

/**
 * Class to describe address (field of organization)
 */
public class Address implements Serializable {
    private String street; //Строка не может быть пустой, Поле может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле может быть null

    /**
     * Constructor for field Address
     * @param zipCode zipcode of organization
     * @param street street of organization
     * @param town town of organization
     */
    public Address(String zipCode, String street, Location town){
        this.zipCode = zipCode;
        this.street = street;
        this.town = town;
    }


    /**
     * Constructor of field: Address, for command update
     * @param postalAddress new postal address of organization
     * @param old old postal address of organization
     */
    public Address(String postalAddress, Address old) {
        if (postalAddress.contains(", "))
        {
            try {
                //String[] split = Arrays.stream(postalAddress.split(Pattern.quote("[,\s]\s*"))).map(String::trim).toArray(String[]::new);
                String[] split = postalAddress.split(", ");
                if (split[0].equals("-"))
                    this.zipCode = old.getZipCode();
                if (split[1].equals("-"))
                    this.street = old.getStreet();
                else
                    this.street = split[1];
                if (split[2].equals("-"))
                    this.town = old.getTown();
                else
                    this.town = new Location(postalAddress);
            } catch (IndexOutOfBoundsException e){
                System.out.println("Incorrect data");
            }
        }
    }

    /**
     * Function to get location of organization
     * @return location
     */
    private Location getTown() {
        return this.town;
    }

    /**
     * Function to get street of organization
     * @return street
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Function to get zipcode of organization
     * @return zipcode
     */
    private String getZipCode() {
        return this.zipCode;
    }

    /**
     * Function to print field: address
     */
    public String print(){
        return " - ZipCode = " + this.zipCode + "\n" +
        " - Street = " + this.street + "\n" +
        town.print();
    }

    /**
     * Function to get field address in format xml
     * @return xml string in format xml which describe fields address
     */
    public String getAddressinXML(){
        String start = "\t\t<postalAddress>\n";
        String zipCode = "\t\t\t<zipCode>" + this.zipCode + "</zipCode>\n";
        String street = "\t\t\t<street>" + this.street + "</street>\n";
        String location = town.getLocationinXML();
        String end = "\t\t</postalAddress>\n";
        return start + zipCode + street + location + end;
    }

    /**
     * Function to get address in xml format
     * @return address
     */
    public String getAddress() {
        return getZipCode() + ", " + getStreet() + ", " + this.town.getTown();
    }
    /**
     * Function to get zipcode
     * @return zipcode
     */
    public String getZipcode() {return this.zipCode;}
    /**
     * Function to get location
     * @return class location
     */
    public Location getLocation() {return this.town;}
}