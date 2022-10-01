package com.rwev.registration;

import com.rwev.appuser.AppUser;
import com.rwev.appuser.AppUserRole;
import com.rwev.appuser.AppUserRepository;
import com.rwev.appuser.AppUserService;
import com.rwev.email.EmailSender;
import com.rwev.registration.token.ConfirmationToken;
import com.rwev.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserRepository appUserRepository;
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    private final EmailSender emailSenderService;
    private final ConfirmationTokenService confirmationTokenService;
    @Override
    public String register(RegistrationReqDto request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        String token =  appUserService.signUp(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));

        return token;
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
