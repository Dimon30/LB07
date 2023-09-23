/* FILE NAME   : OrganizationType.java
 * PROGRAMMER  : DS6
 * @author     : Sokolov Dmitry
 * LAST UPDATE : 30.04.2023
 * PURPOSE     : Type of Organization
 */

package Organization;

import java.io.Serializable;

/**
 * Enum class for working with type of organizations
 */
public enum OrganizationType implements Serializable {
    COMMERCIAL("commercial"),
    PUBLIC("public"),
    GOVERNMENT("government"),
    TRUST("trust"),
    PRIVATE_LIMITED_COMPANY("private_limited_company");

    private final String name;

    /**
     * Constructor of field: OrganizationType
     * @param name name of organization type
     */
    OrganizationType(String name){
        this.name = name;
    }

    /**
     * Function to get type of organizations in format object: OrganizationType
     * @param s type of organization in string format
     * @return type of organization
     */
    public static OrganizationType findTypeByName(String s) {
        switch (s) {
            case ("commercial"), ("Commercial"), ("COMMERCIAL") -> {return COMMERCIAL;}
            case ("public"), ("Public"), ("PUBLIC") -> {return PUBLIC;}
            case ("government"), ("Government"), ("GOVERNMENT") -> {return GOVERNMENT;}
            case ("trust"), ("Trust"), ("TRUST") -> {return TRUST;}
            case ("private_limited_company"), ("Private limited company"), ("PRIVATE_LIMITED_COMPANY"), ("PrivateLimitedCompany"), ("Private_Limited_Company") -> {return PRIVATE_LIMITED_COMPANY;}
            default -> {return null;}
        }
    }

    /**
     * Function to get organization type in string format
     * @param t organization type
     * @return type organization type in string format
     */
    public static String findNamebyType(OrganizationType t) {
        return t.name;
    }

}
