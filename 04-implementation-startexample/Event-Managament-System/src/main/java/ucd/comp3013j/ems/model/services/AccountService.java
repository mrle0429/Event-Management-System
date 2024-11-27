package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.entities.Account;
import ucd.comp3013j.ems.model.entities.Administrator;
import ucd.comp3013j.ems.model.entities.Customer;
import ucd.comp3013j.ems.model.entities.Organiser;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.repos.AdminRepository;
import ucd.comp3013j.ems.model.repos.CustomerRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final OrganiserRepository organiserRepository;

    @Autowired
    public AccountService(CustomerRepository cr, AdminRepository ar, OrganiserRepository or) {
        this.customerRepository = cr;
        this.adminRepository = ar;
        this.organiserRepository = or;
    }

    public Account getAccount(long id) {
        Account a = customerRepository.findById(id).orElse(null);
        if (a == null){
            a = adminRepository.findById(id).orElse(null);
            if (a == null){
                a = organiserRepository.findById(id).orElse(null);
            }
        }
        return a;
    }

    public Account getAccount(String email) {
        Account a = customerRepository.findByEmail(email);
        if (a == null){
            a = adminRepository.findByEmail(email);
            if (a == null){
                a = organiserRepository.findByEmail(email);
            }
        }
        return a;
    }

    public void saveUser(AccountDTO registration) {
        Customer c = new Customer(registration);
        customerRepository.save(c);
    }

    public List<Account> getAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.addAll(customerRepository.findAll());
        accounts.addAll(adminRepository.findAll());
        accounts.addAll(organiserRepository.findAll());
        return accounts;
    }

    public Administrator getAdminAccount(String email) {
        return adminRepository.findByEmail(email);
    }

    public Customer getCustomerAccount(String email) {
        return customerRepository.findByEmail(email);
    }

    public Organiser getOrganiserAccount(String email) {
        return organiserRepository.findByEmail(email);
    }


    /**
     * 管理员创建账户
     * @param accountDTO
     */
    public void createAccount(AccountDTO accountDTO) {
        if (customerRepository.findByEmail(accountDTO.getEmail()) != null ||
            adminRepository.findByEmail(accountDTO.getEmail()) != null ||
            organiserRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }

        accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));

        Account account;
        switch (accountDTO.getRole()) {
            case "ADMINISTRATOR":
                account = new Administrator(accountDTO.getEmail(), accountDTO.getName(), accountDTO.getPassword());
                adminRepository.save((Administrator) account);
                break;
            case "ORGANISER":
                account = new Organiser(accountDTO.getEmail(), accountDTO.getName(), accountDTO.getPassword(), 
                                      accountDTO.getCompanyName(), accountDTO.getCompanyAddress(), accountDTO.getCompanyPhone());
                organiserRepository.save((Organiser) account);
                break;
            case "USER":
                account = new Customer(accountDTO.getEmail(), accountDTO.getName(), accountDTO.getPassword());
                customerRepository.save((Customer) account);
                break;
            default:
                throw new RuntimeException("Invalid role");
        }
    }
}
