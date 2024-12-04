package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Event;
import ucd.comp3013j.ems.model.entities.Ticket;
import ucd.comp3013j.ems.model.enums.TicketType;
import ucd.comp3013j.ems.model.services.AccountService;
import ucd.comp3013j.ems.model.services.EventService;
import ucd.comp3013j.ems.model.services.TicketService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import java.util.List;

/**
 * Controller for handling ticket-related operations.
 * This controller manages ticket purchases, confirmations, and ticket viewing.
 */
@Controller
@RequestMapping("/tickets")
public class TicketSystem {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AccountService accountService;

    /**
     * Displays the ticket purchase form for a specific event.
     * Accessible by: All authenticated users
     * @param eventId ID of the event to purchase tickets for
     * @param model Spring MVC Model object
     * @return The ticket purchase page view or redirects to available events with error
     */
    @GetMapping("/purchase/{eventId}")
    public String showPurchaseForm(@PathVariable Long eventId, Model model) {
        Event event = eventService.getEvent(eventId);
        if (event == null) {
            return "redirect:/events/available?error=event_not_found";
        }

        model.addAttribute("event", event);
        model.addAttribute("ticketTypes", TicketType.values());
        return "tickets/purchase";
    }

    /**
     * Processes a ticket purchase request.
     * Accessible by: Authenticated customers
     * @param eventId ID of the event to purchase tickets for
     * @param ticketType Type of ticket being purchased (e.g., VIP, Standard)
     * @param quantity Number of tickets to purchase
     * @param authentication Current user's authentication information
     * @param model Spring MVC Model object
     * @return Redirects to confirmation page on success, or purchase page with error
     */
    @PostMapping("/purchase/{eventId}")
    public String purchaseTicket(
            @PathVariable Long eventId,
            @RequestParam TicketType ticketType,
            @RequestParam Integer quantity,
            Authentication authentication,
            Model model) {
        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                Customer customer = accountService.getCustomerAccount(aw.getUsername());
                Event event = eventService.getEvent(eventId);

                if (event == null) {
                    return "redirect:/events/available?error=event_not_found";
                }

                List<Ticket> tickets = ticketService.purchaseTickets(event, customer, ticketType, quantity);
                return "redirect:/tickets/confirmation/" + tickets.get(0).getId() + "?quantity=" + quantity;
            }
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/tickets/purchase/" + eventId + "?error=" + e.getMessage();
        }
    }

    /**
     * Displays the ticket purchase confirmation page.
     * Accessible by: Ticket purchaser
     * @param ticketId ID of the purchased ticket
     * @param quantity Number of tickets purchased
     * @param model Spring MVC Model object
     * @return The confirmation page view or redirects to available events with error
     */
    @GetMapping("/confirmation/{ticketId}")
    public String showConfirmation(@PathVariable Long ticketId, @RequestParam Integer quantity, Model model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            return "redirect:/events/available?error=ticket_not_found";
        }

        model.addAttribute("ticket", ticket);
        model.addAttribute("quantity", quantity);
        return "tickets/confirmation";
    }

    /**
     * Displays all tickets owned by the current user.
     * Accessible by: Authenticated customers
     * @param authentication Current user's authentication information
     * @param model Spring MVC Model object
     * @return The user's tickets page view or redirects to login page
     */
    @GetMapping("/my-tickets")
    public String showMyTickets(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Customer customer = accountService.getCustomerAccount(aw.getUsername());
            List<Ticket> tickets = ticketService.getCustomerTickets(customer);
            model.addAttribute("tickets", tickets);
            return "tickets/my-tickets";
        }
        return "redirect:/login";
    }

} 