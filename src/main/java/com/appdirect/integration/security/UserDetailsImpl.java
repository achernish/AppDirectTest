package com.appdirect.integration.security;

import com.appdirect.dto.CustomerDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Collection<GrantedAuthority> authorities = Collections.unmodifiableList(AuthorityUtils.createAuthorityList("USER"));
    private final CustomerDTO customerDTO;

    @Override
    public String getUsername() {
        return String.valueOf(customerDTO.getId());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}