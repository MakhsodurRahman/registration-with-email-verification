package com.rwev.appuser;

import com.rwev.appuser.AppUser;
import com.rwev.appuser.AppUserRepository;
import com.rwev.appuser.AppUserService;
import com.rwev.registration.token.ConfirmationToken;
import com.rwev.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


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

      //send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }


}
