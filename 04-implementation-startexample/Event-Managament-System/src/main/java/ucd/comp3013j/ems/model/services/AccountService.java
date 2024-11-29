package ucd.comp3013j.ems.model.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.entities.*;
import ucd.comp3013j.ems.model.dto.AccountDTO;
import ucd.comp3013j.ems.model.repos.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AccountService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final OrganiserRepository organiserRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private TicketRepository ticketRepository;

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

//    public void updateAccount(AccountDTO accountDTO){
//        Account account = null;
//        if (accountDTO.getRole().equals("ADMINISTRATOR")){
//            account = adminRepository.findById(accountDTO.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
//            account.setName(accountDTO.getName());
//            account.setEmail(accountDTO.getEmail());
//            adminRepository.save((Administrator) account);
//        } else if (accountDTO.getRole().equals("ORGANISER")) {
//            account = organiserRepository.findById(accountDTO.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
//            account.setName(accountDTO.getName());
//            account.setEmail(accountDTO.getEmail());
//            Organiser organiser = (Organiser) account;
//            organiser.setCompanyName(accountDTO.getCompanyName());
//            organiser.setAddress(accountDTO.getCompanyAddress());
//            organiser.setPhoneNumber(accountDTO.getCompanyPhone());
//            organiserRepository.save(organiser);
//        }else{
//            account = customerRepository.findById(accountDTO.getId()).orElseThrow(() -> new RuntimeException("Account not found"));
//            account.setName(accountDTO.getName());
//            account.setEmail(accountDTO.getEmail());
//            customerRepository.save((Customer) account);
//        }
//    }

    public void deleteCustomer(Customer account){
        List<Ticket> tickets = ticketRepository.findByCustomer(account);

        // 删除相关的票
        for (Ticket ticket : tickets) {
            ticketRepository.delete(ticket);
        }

        customerRepository.deleteById(account.getId());
    }

    public void deleteOrganiser(Long organiserId){
        List<Event> events = eventRepository.findByOrganiserId(organiserId);

        // 把所有的活动保留，组织者设置为null
        for (Event event : events) {
            event.setOrganiser(null);
            eventRepository.save(event);
        }

        organiserRepository.deleteById(organiserId);
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

    public List<Event> findEventsByOrganiser(Organiser organiser) {
        return eventRepository.findByOrganiser(organiser);
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
                                      accountDTO.getCompanyName(), accountDTO.getAddress(), accountDTO.getPhoneNumber());
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

    public void updateAccount(AccountDTO accountDTO) {
        Account account = getAccount(accountDTO.getEmail());
        if (account == null) {
            throw new RuntimeException("Account not found");
        }

        account.setName(accountDTO.getName());
        
        if (account instanceof Administrator) {
            Administrator admin = (Administrator) account;
            adminRepository.save(admin);
        } else if (account instanceof Organiser) {
            Organiser organiser = (Organiser) account;
            if (accountDTO.getRole().equals("ORGANISER")) {
                organiser.setCompanyName(accountDTO.getCompanyName());
                organiser.setAddress(accountDTO.getAddress());
                organiser.setPhoneNumber(accountDTO.getPhoneNumber());
            }
            organiserRepository.save(organiser);
        } else if (account instanceof Customer) {
            Customer customer = (Customer) account;
            customerRepository.save(customer);
        }
    }

    public void changePassword(String email, String currentPassword, String newPassword) {
        Account account = getAccount(email);
        if (account == null) {
            throw new RuntimeException("Account not found");
        }
        
        if (!passwordEncoder.matches(currentPassword, account.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        
        account.setPassword(passwordEncoder.encode(newPassword));
        
        if (account instanceof Administrator) {
            adminRepository.save((Administrator) account);
        } else if (account instanceof Organiser) {
            organiserRepository.save((Organiser) account);
        } else if (account instanceof Customer) {
            customerRepository.save((Customer) account);
        }
    }
}
