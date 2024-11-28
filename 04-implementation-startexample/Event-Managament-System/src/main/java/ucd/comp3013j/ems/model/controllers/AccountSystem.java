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

import ucd.comp3013j.ems.model.entities.*;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.services.AccountService;
import ucd.comp3013j.ems.model.services.TicketService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;



import java.util.List;

@Controller
public class AccountSystem {
    private final AccountService accountService;
    private final TicketService ticketService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public AccountSystem(AccountService as, TicketService ts) {
        this.accountService = as;
        this.ticketService = ts;
    }

    @GetMapping(value={"/", "","/login", "/login/"})
    public String login(Model model) {
        //model.addAttribute("registration", new AccountDTO());
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("registration", new AccountDTO());
        return "signup";

    }

    @PostMapping("/register")
    public String registerPost(
            @ModelAttribute("registration") AccountDTO registration,
            BindingResult result,
            Model model) {
        registration.setPassword(bCryptPasswordEncoder.encode(registration.getPassword()));
        Account existingUser = accountService.getAccount(registration.getEmail());
        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
            model.addAttribute("user", registration);
            return "signup";
        }
        accountService.saveUser(registration);
        return "redirect:/login?register_success";
    }

    @GetMapping(value = {"/administrator", "/administrator/"})
    public String adminPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Administrator account = accountService.getAdminAccount(aw.getUsername());
            model.addAttribute("account", account);
        }

        System.out.println("Admin page");
        List<Account> accounts  = accountService.getAccounts();
        System.out.println("Number of accounts: " + accounts.size());
        model.addAttribute("accounts", accounts);
        return "main-admin";
    }

    @GetMapping(value = {"/customer", "/customer/"})
    public String customerPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Customer customer = accountService.getCustomerAccount(aw.getUsername());
            List<Ticket> tickets = ticketService.getCustomerTickets(customer);
            
            model.addAttribute("customer", customer);
            model.addAttribute("tickets", tickets);
        }
        return "main-customer";
    }

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
     * 跳转管理员创建账户页面
     * @param model
     * @return
     */
    @GetMapping("/create-account")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("accountDTO", new AccountDTO());
        return "account/create-account";
    }


    /**
     * 处理管理员创建用户表单
     * @param accountDTO
     * @param model
     * @return
     */
    @PostMapping("/create-account")
    public String createAccount(@ModelAttribute AccountDTO accountDTO, Model model) {
        try {
            if (accountDTO.getRole().equals("CUSTOMER")) {
                accountDTO.setRole("USER");
            }
            accountService.createAccount(accountDTO);
            return "redirect:/administrator";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "account/create-account";
        }
    }

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
            return "account/edit-account";
        }
        return "redirect:/login";
    }

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
            return "account/edit-account";
        }
    }

    @GetMapping("/account/change-password")
    public String showChangePassword(Model model) {
        return "account/change-password";
    }

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
