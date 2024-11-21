package ucd.comp3013j.ems.websecurity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ucd.comp3013j.ems.model.Account;

import java.util.Arrays;
import java.util.Collection;

public class AccountWrapper implements UserDetails {
    private final Account account;

    public AccountWrapper(Account user) {
        this.account = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(account.getRole().toString());
        System.out.println("AccountWrapper.getAuthorities: " + Arrays.asList(authority));
        return Arrays.asList(authority);
    }

    public String getPassword() {
        return account.getPassword();
    }

    public String getUsername() {
        return account.getEmail();
    }
}
