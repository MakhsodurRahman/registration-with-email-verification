package com.rwev.service.implementation;

import com.rwev.dto.RegistrationReqDto;
import com.rwev.entity.AppUser;
import com.rwev.enums.AppUserRole;
import com.rwev.repository.AppUserRepository;
import com.rwev.service.EmailValidator;
import com.rwev.service.definition.AppUserService;
import com.rwev.service.definition.RegistrationService;
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
