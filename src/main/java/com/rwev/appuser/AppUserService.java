package com.rwev.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    public UserDetails loadUserByUsername(String username);
    public String signUp(AppUser appUser);
    public int enableAppUser(String email);

}
