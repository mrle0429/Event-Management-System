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

import ucd.comp3013j.ems.model.entities.Account;
import ucd.comp3013j.ems.model.entities.Administrator;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.services.AccountService;
import ucd.comp3013j.ems.websecurity.AccountWrapper;



import java.util.List;

@Controller
public class AccountSystem {
    private final AccountService accountService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    public AccountSystem(AccountService as) {
        this.accountService = as;
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
            Customer account = accountService.getCustomerAccount(aw.getUsername());
            model.addAttribute("customer", account);
        }
        return "main-customer";
    }

    @GetMapping(value = {"/organiser", "/organiser/"})
    public String organiserPage(Authentication authentication, Model model) {
        if (authentication.getPrincipal() instanceof AccountWrapper aw) {
            Organiser account = accountService.getOrganiserAccount(aw.getUsername());
            model.addAttribute("organiser", account);
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
        return "create-account";
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
            return "create-account";
        }
    }



}
