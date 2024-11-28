package ucd.comp3013j.ems.websecurity;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ucd.comp3013j.ems.model.services.AccountDetailsService;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/").permitAll()
                .requestMatchers("/register*").permitAll()
                .requestMatchers("/login*").permitAll()
                .requestMatchers("/administrator").hasAuthority("ADMINISTRATOR")
                .requestMatchers("/deleteCustomer").hasAuthority("ADMINISTRATOR")
                .requestMatchers("/deleteOrganiser").hasAuthority("ADMINISTRATOR")
                .requestMatchers("/customer").hasAuthority("CUSTOMER")
                .requestMatchers("/organiser").hasAuthority("ORGANISER")
                .requestMatchers("/signup").permitAll()
                .requestMatchers("/events/**").hasAnyAuthority("ADMINISTRATOR", "ORGANISER", "CUSTOMER")
                .requestMatchers("/events/create").hasAnyAuthority("ADMINISTRATOR", "ORGANISER")
                .requestMatchers("/venue/**").hasAnyAuthority("ADMINISTRATOR", "ORGANISER")
                .requestMatchers("/detail").hasAnyAuthority("ADMINISTRATOR", "ORGANISER", "CUSTOMER")
                .requestMatchers("/edit").hasAnyAuthority("ADMINISTRATOR", "ORGANISER", "CUSTOMER")

                .anyRequest().authenticated()
                )
            .formLogin((form) -> form.loginPage("/login").loginProcessingUrl("/login")
                .successHandler(new AuthSuccess())
                .permitAll()
            )
            .logout( (logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
            );
        return http.build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountDetailsService();
    }

}
