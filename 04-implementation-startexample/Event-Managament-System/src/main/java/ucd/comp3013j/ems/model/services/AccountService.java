package ucd.comp3013j.ems.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.Account;
import ucd.comp3013j.ems.model.Administrator;
import ucd.comp3013j.ems.model.Customer;
import ucd.comp3013j.ems.model.Organiser;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.repos.AdminRepository;
import ucd.comp3013j.ems.model.repos.CustomerRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
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
}
