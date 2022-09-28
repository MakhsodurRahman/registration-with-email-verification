package com.rwev.service.definition;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
}
