package ucd.comp3013j.ems.model.enums;

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
