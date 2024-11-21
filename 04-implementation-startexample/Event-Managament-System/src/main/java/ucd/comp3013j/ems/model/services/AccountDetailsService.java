package ucd.comp3013j.ems.model.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucd.comp3013j.ems.model.Role;
import ucd.comp3013j.ems.model.repos.AdminRepository;
import ucd.comp3013j.ems.model.repos.CustomerRepository;
import ucd.comp3013j.ems.model.repos.OrganiserRepository;
import ucd.comp3013j.ems.model.Account;
import org.springframework.security.core.GrantedAuthority;
import ucd.comp3013j.ems.websecurity.AccountWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AccountDetailsService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrganiserRepository organiserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = adminRepository.findByEmail(username);
        if (user == null) {
            user = customerRepository.findByEmail(username);
        }
        if (user == null) {
            user = organiserRepository.findByEmail(username);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new AccountWrapper(user);
//        return new User(user.getEmail(), bCryptPasswordEncoder.encode(user.getPassword()), mapRolesToAuthorities(user.getRole()));
    }

    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        System.out.println("AccountDetailsService.mapRolesToAuthorities: " + role.toString());
        System.out.println("AccountDetailsService.mapRolesToAuthorities: " + new SimpleGrantedAuthority(role.toString()));
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }
}
