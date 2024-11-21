package ucd.comp3013j.ems.websecurity;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class AuthSuccess implements AuthenticationSuccessHandler {
    SimpleUrlAuthenticationSuccessHandler customerSuccessHandler = new SimpleUrlAuthenticationSuccessHandler("/customer");
    SimpleUrlAuthenticationSuccessHandler organiserSuccessHandler = new SimpleUrlAuthenticationSuccessHandler("/organiser");
    SimpleUrlAuthenticationSuccessHandler adminSuccessHandler = new SimpleUrlAuthenticationSuccessHandler("/administrator");
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ADMINISTRATOR")) {
                this.adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            } else if (authorityName.equals("ORGANISER")) {
                this.organiserSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            } else if (authorityName.equals("CUSTOMER")) {
                this.customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        }
    }
}

