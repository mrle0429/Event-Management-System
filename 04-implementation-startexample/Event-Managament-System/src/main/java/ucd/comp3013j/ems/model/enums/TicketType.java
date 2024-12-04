package ucd.comp3013j.ems.model.enums;

import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Ticket;
import ucd.comp3013j.ems.model.entities.Venue;

/**
 * Enumeration of ticket types available for events.
 * 
 * Available types:
 * - PREMIUM: Premium tickets with best seating and potential additional benefits
 * - STANDARD: Standard tickets with regular seating
 * - ECONOMY: Economy tickets with basic seating options
 * 
 * Used for:
 * - Defining ticket pricing tiers
 * - Managing seating capacity by level
 * - Tracking ticket sales by category
 * 
 * @see Event
 * @see Ticket
 * @see Venue
 */
public enum TicketType {
    PREMIUM,
    STANDARD,
    ECONOMY
} 