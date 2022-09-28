package com.rwev.service.definition;

import com.rwev.entity.AppUser;
import com.rwev.enums.AppUserRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
    public String signUp(AppUser appUser);

}
