package com.sewell.common.security.password;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PasswordAuthenticationToken extends AbstractAuthenticationToken {

    private String username;

    private String password;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public PasswordAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public PasswordAuthenticationToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
        setAuthenticated(false);
    }

    public PasswordAuthenticationToken(String username, String password,
                                               Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = password;
        super.setAuthenticated(true); // must use super, as we override
    }
    @Override
    public Object getCredentials() {
        return this.password;
    }

    @Override
    public Object getPrincipal() {
        return this.username;
    }

    public static PasswordAuthenticationToken unauthenticated(String username, String password) {
        return new PasswordAuthenticationToken(username, password);
    }

    public static PasswordAuthenticationToken authenticated(String username, String password,
                                                                    Collection<? extends GrantedAuthority> authorities) {
        return new PasswordAuthenticationToken(username, password, authorities);
    }
}
