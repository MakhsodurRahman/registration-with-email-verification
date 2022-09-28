package com.rwev.registration;

import com.rwev.appuser.AppUser;
import com.rwev.appuser.AppUserRole;
import com.rwev.appuser.AppUserRepository;
import com.rwev.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserRepository appUserRepository;
    private final EmailValidator emailValidator;
    private final AppUserService appUserService;
    @Override
    public String register(RegistrationReqDto request) {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUp(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }
}
