package com.rwev.service.implementation;

import com.rwev.entity.AppUser;
import com.rwev.repository.AppUserRepository;
import com.rwev.service.definition.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("user not found"));
    }

    public String signUp(AppUser appUser){
      boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
      if(userExists){
          throw new IllegalStateException("email already taken");
      }
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      appUserRepository.save(appUser);
        return "user save database";
    }
}
