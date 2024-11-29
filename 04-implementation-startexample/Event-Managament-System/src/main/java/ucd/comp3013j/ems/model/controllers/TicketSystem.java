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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tickets")
public class TicketSystem {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AccountService accountService;

    // 显示购票页面
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

    // 处理购票请求
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

    // 显示购票确认页面
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

    // 显示用户的所有票
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