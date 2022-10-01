package com.rwev.registration;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRegistrationControllerImpl implements UserRegistrationController {

    private final RegistrationService registrationService;
    @Override
    public String register(RegistrationReqDto registrationReqDto) {

        return registrationService.register(registrationReqDto);
    }

    @Override
    public String confirm(String token) {
       return registrationService.confirmToken(token);
    }
}
