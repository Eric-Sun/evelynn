package com.j13.evelynn.security.service;

import com.j13.evelynn.net.AccountServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class WebDaoAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private AccountServerManager accountServerManager;

    /**
     * 重新此方法，写第三方登陆校验
     */
    @SuppressWarnings("deprecation")
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String username = userDetails.getUsername();
        String password = authentication.getCredentials().toString();

        if (this.check(username, password)) {
            return;
        } else {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

    }

    private boolean check(String username, String password) {
        return accountServerManager.checkExisted(username, password);
    }

}
