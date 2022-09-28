package com.rwev.controller.implementation;

import com.rwev.controller.definition.UserRegistrationController;
import com.rwev.dto.RegistrationReqDto;
import com.rwev.service.definition.RegistrationService;
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
}
