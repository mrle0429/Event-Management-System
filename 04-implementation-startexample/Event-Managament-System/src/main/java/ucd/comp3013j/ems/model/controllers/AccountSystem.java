package ucd.comp3013j.ems.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.entities.*;
import ucd.comp3013j.ems.model.enums.Role;
import ucd.comp3013j.ems.model.services.AccountService;
import ucd.comp3013j.ems.model.services.EventService;
import ucd.comp3013j.ems.model.services.TicketService;
import ucd.comp3013j.ems.model.services.VenueService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import java.util.List;

@Controller
public class AccountSystem {
    private final AccountService accountService;
    private final TicketService ticketService;
    private final EventService eventService;
    private final VenueService venueService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AccountSystem(AccountService as, TicketService ts, EventService es, VenueService vs) {
        this.accountService = as;
        this.ticketService = ts;
        this.eventService = es;
        this.venueService = vs;
    }

    /**
     * Handles GET requests for the login page.
     * Accessible by: All users
     *
     * @param model Spring MVC Model object
     * @return The login page view
     */
    @GetMapping(value = {"/", "", "/login", "/login/"})
    public String login(Model model) {
        //model.addAttribute("registration", new AccountDTO());
        return "login";
    }

    /**
     * Handles GET requests for the signup page.
     * Accessible by: Unauthenticated users
     *
     * @param model Spring MVC Model object
     * @return The signup page view
     */
    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("registration", new AccountDTO());
        return "signup";

    }

    /**
     * Handles POST requests for user registration.
     * Accessible by: Unauthenticated users
     *
     * @param registration Registration form data
     * @param result       Form validation result
     * @param model        Spring MVC Model object
     * @return Redirects to login page on success, or returns signup page on failure
     */
    @PostMapping("/register")
    public String registerPost(
            @ModelAttribute("registration") AccountDTO registration,
            BindingResult result,
            Model model) {
        registration.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        Account existingUser = accountService.getAccount(registration.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", registration);
            return "signup";
        }
        accountService.saveUser(registration);
        return "redirect:/login?register_success";
    }

    /**
     * Displays the administrator home page.
     * Accessible by: Administrators
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The main admin page view or redirects to login page
     */
    @GetMapping(value = {"/administrator", "/administrator/"})
    public String adminPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Administrator account = accountService.getAdminAccount(aw.getUsername());
            model.addAttribute("account", account);


            //System.out.println("Admin page");
            List<Account> accounts = accountService.getAccounts();
            accounts.remove(account);
            accounts.add(0, account);
            //System.out.println("Number of accounts: " + accounts.size());
            model.addAttribute("accounts", accounts);

            List<Event> events = eventService.getAllEvents();
            List<Venue> venues = venueService.getAllVenues();
            int eventCount = events.size();
            int venueCount = venues.size();
            model.addAttribute("eventCount", eventCount);
            model.addAttribute("venueCount", venueCount);
            return "main-admin";
        }
        return "redirect:/login";
    }

    /**
     * Displays the customer home page.
     * Accessible by: Customers
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The main customer page view
     */
    @GetMapping(value = {"/customer", "/customer/"})
    public String customerPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Customer customer = accountService.getCustomerAccount(aw.getUsername());
            List<Ticket> tickets = ticketService.getUpcomingTickets(customer);

            model.addAttribute("customer", customer);
            model.addAttribute("tickets", tickets);
        }
        return "main-customer";
    }

    /**
     * Displays the organiser home page.
     * Accessible by: Organisers
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The main organiser page view
     */
    @GetMapping(value = {"/organiser", "/organiser/"})
    public String organiserPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Organiser account = accountService.getOrganiserAccount(aw.getUsername());
            model.addAttribute("organiser", account);

            List<Event> events = accountService.findEventsByOrganiser(account);
            model.addAttribute("events", events);
        }
        return "main-organiser";
    }

    /**
     * Displays account details.
     * Accessible by: Administrators and the account owner
     *
     * @param userEmail      Email of the user to view (optional)
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The account detail page view
     */
    @GetMapping("/detail")
    public String viewAccount(@RequestParam(required = false) String userEmail, Authentication authentication, Model model) {

        Account account = null;

        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                String email = aw.getUsername();
                if (aw.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                    // 管理员通过用户ID获取账户信息
                    account = accountService.getAccount(userEmail);
                } else {
                    // 非管理员只能查看自己的信息
                    account = accountService.getAccount(email);
                }

                if (account.getRole() == Role.ORGANISER) {
                    Organiser organiser = (Organiser) account;
                    model.addAttribute("account", organiser);
                    return "account/detail";
                }

                model.addAttribute("account", account);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "account/detail";
    }

    /**
     * Displays the account edit page.
     * Accessible by: Administrators and the account owner
     *
     * @param userEmail      Email of the user to edit (optional)
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The account edit page view
     */
    @GetMapping("/edit")
    public String editAccount(@RequestParam(required = false) String userEmail, Authentication authentication, Model model) {

        Account account = null;
        AccountDTO accountDTO = new AccountDTO();

        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                String email = aw.getUsername();
                if (aw.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                    // 管理员通过用户ID获取账户信息
                    account = accountService.getAccount(userEmail);

                } else {
                    // 非管理员只能修改自己的信息
                    account = accountService.getAccount(email);
                }
                accountDTO.setId(account.getId());
                accountDTO.setName(account.getName());
                accountDTO.setEmail(account.getEmail());
                accountDTO.setRole(String.valueOf(account.getRole()));

                if (account.getRole() == Role.ORGANISER) {
                    Organiser organiser = (Organiser) account;
                    accountDTO.setCompanyName(organiser.getCompanyName());
                    accountDTO.setAddress(organiser.getAddress());
                    accountDTO.setPhoneNumber(organiser.getPhoneNumber());
                }

                model.addAttribute("accountDTO", accountDTO);
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "account/admin-update-account";
    }

    /**
     * Updates account information.
     * Accessible by: Administrators and the account owner
     *
     * @param accountDTO     Account data transfer object
     * @param authentication Current user's authentication information
     * @return Redirects to account detail page or logout
     */
    @PostMapping("/edit")
    public String updateAccount(@ModelAttribute AccountDTO accountDTO, Authentication authentication) {

        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            accountService.updateAccount(accountDTO);
            if (aw.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                return "redirect:/detail?userEmail=" + accountDTO.getEmail();
            }
        }

        return "redirect:/detail";
    }

    /**
     * Deletes a customer account.
     * Accessible by: Administrators
     *
     * @param customerId ID of the customer to delete
     * @return Redirects to administrator page
     */
    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam Long customerId, RedirectAttributes redirectAttributes, Authentication authentication) {
        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                if (!aw.getAuthorities().stream()
                        .anyMatch(a -> a.getAuthority().equals("ADMINISTRATOR"))) {
                    redirectAttributes.addFlashAttribute("message", "Only administrators can delete accounts");
                    redirectAttributes.addFlashAttribute("messageType", "error");
                    return "redirect:/administrator";
                }
    

                Customer customer = (Customer) accountService.getAccount(customerId);
                accountService.deleteCustomer(customerId);
                redirectAttributes.addFlashAttribute("message", 
                    String.format("Successfully deleted customer account: %s", customer.getName()));
                redirectAttributes.addFlashAttribute("messageType", "success");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Error deleting customer account: " + e.getMessage());
            redirectAttributes.addFlashAttribute("messageType", "error");
        }
        return "redirect:/administrator";
    }

    /**
     * Deletes an organiser account.
     * Accessible by: Administrators
     *
     * @param organiserId ID of the organiser to delete
     * @return Redirects to administrator page
     */
    @PostMapping("/deleteOrganiser")
    public String deleteOrganiser(@RequestParam Long organiserId) {
        accountService.deleteOrganiser(organiserId);
        return "redirect:/administrator";
    }

    /**
     * Displays the create account form for administrators.
     * Accessible by: Administrators
     *
     * @param model Spring MVC Model object
     * @return The create account page view
     */
    @GetMapping("/create-account")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "account/create-account";
    }

    /**
     * Handles the creation of a new account by an administrator.
     * Accessible by: Administrators
     *
     * @param accountDTO Account data transfer object
     * @param model      Spring MVC Model object
     * @return Redirects to administrator page or returns create account page on error
     */
    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute AccountDTO accountDTO, Model model, Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            if (accountDTO.getRole().equals("CUSTOMER")) {
                accountDTO.setRole("CUSTOMER");
            }
            accountService.createAccount(accountDTO);
            redirectAttributes.addFlashAttribute("message", "Account created successfully!");
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/administrator";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "account/create-account";
        }
    }

    /**
     * Displays the current user's account information.
     * Accessible by: All authenticated users
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The my account page view or redirects to login page
     */
    @GetMapping("/account/my-account")
    public String showMyAccount(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            String email = aw.getUsername();
            Account account = accountService.getAccount(email);

            // 根据不同角色获取详细信息
            switch (account.getRole()) {
                case ADMINISTRATOR:
                    Administrator admin = accountService.getAdminAccount(email);
                    model.addAttribute("account", admin);
                    break;
                case ORGANISER:
                    Organiser organiser = accountService.getOrganiserAccount(email);
                    model.addAttribute("account", organiser);
                    break;
                case CUSTOMER:  // CUSTOMER
                    Customer customer = accountService.getCustomerAccount(email);
                    model.addAttribute("account", customer);
                    break;
            }

            return "account/my-account";
        }

        // 如果没有认证信息，重定向到登录页面
        return "redirect:/login";
    }

    /**
     * Displays the edit account page for the current user.
     * Accessible by: All authenticated users
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The edit account page view or redirects to login page
     */
    @GetMapping("/account/edit")
    public String showEditAccount(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Account account = accountService.getAccount(aw.getUsername());
            AccountDTO accountDTO = new AccountDTO();
            // 填充基本信息
            accountDTO.setEmail(account.getEmail());
            accountDTO.setName(account.getName());
            accountDTO.setRole(String.valueOf(account.getRole()));

            // 如果是组织者，填充额外信息
            if (account instanceof Organiser organiser) {
                accountDTO.setCompanyName(organiser.getCompanyName());
                accountDTO.setAddress(organiser.getAddress());
                accountDTO.setPhoneNumber(organiser.getPhoneNumber());
            }

            model.addAttribute("accountDTO", accountDTO);
            return "account/edit-profile";
        }
        return "redirect:/login";
    }

    /**
     * Updates the current user's account information.
     * Accessible by: All authenticated users
     *
     * @param accountDTO     Account data transfer object
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return Redirects to my account page or returns edit account page on error
     */
    @PostMapping("/account/edit")
    public String updateAccount(
            @ModelAttribute AccountDTO accountDTO,
            Authentication authentication,
            Model model) {
        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                accountDTO.setEmail(aw.getUsername()); // 保持邮箱不变
                accountService.updateAccount(accountDTO);
                return "redirect:/account/my-account?update_success";
            }
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "account/edit-profile";
        }
    }

    /**
     * Displays the change password page.
     * Accessible by: All authenticated users
     *
     * @param model Spring MVC Model object
     * @return The change password page view
     */
    @GetMapping("/account/change-password")
    public String showChangePassword(Model model) {
        return "account/change-password";
    }

    /**
     * Changes the current user's password.
     * Accessible by: All authenticated users
     *
     * @param currentPassword Current password
     * @param newPassword     New password
     * @param authentication  Current user's authentication information
     * @param model           Spring MVC Model object
     * @return Redirects to my account page or returns change password page on error
     */
    @PostMapping("/account/change-password")
    public String changePassword(
            @RequestParam String currentPassword,
            @RequestParam String newPassword,
            Authentication authentication,
            Model model) {
        try {
            if (authentication.getPrincipal() instanceof AccountWrapper aw) {
                accountService.changePassword(aw.getUsername(), currentPassword, newPassword);
                return "redirect:/account/my-account?password_changed";
            }
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "account/change-password";
        }
    }

    /**
     * Displays the customer's tickets.
     * Accessible by: Customers
     *
     * @param authentication Current user's authentication information
     * @param model          Spring MVC Model object
     * @return The main customer page view or redirects to login page
     */
    @GetMapping("/customer/tickets")
    public String showCustomerTickets(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Customer customer = accountService.getCustomerAccount(aw.getUsername());
            List<Ticket> tickets = ticketService.getCustomerTickets(customer);
            model.addAttribute("tickets", tickets);
            return "main-customer";
        }
        return "redirect:/login";
    }

}
