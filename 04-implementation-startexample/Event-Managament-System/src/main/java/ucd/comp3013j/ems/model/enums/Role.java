package ucd.comp3013j.ems.model.enums;

import ucd.comp3013j.ems.model.entities.Account;
import ucd.comp3013j.ems.model.entities.Administrator;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Organiser;

/**
 * Enumeration of user roles in the Event Management System.
 * 
 * Available roles:
 * - ADMINISTRATOR: System administrators with full access rights
 * - ORGANISER: Event organizers who can create and manage events
 * - CUSTOMER: Regular users who can browse and purchase tickets
 * 
 * Used for role-based access control throughout the application.
 * 
 * @see Account
 * @see Administrator
 * @see Organiser
 * @see Customer
 */
public enum Role {
    ADMINISTRATOR,
    ORGANISER,
    CUSTOMER
}
