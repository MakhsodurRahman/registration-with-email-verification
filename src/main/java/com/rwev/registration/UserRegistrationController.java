package com.rwev.registration;

import com.rwev.registration.RegistrationReqDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
public interface UserRegistrationController {
    @PostMapping("/registration")
    public String register(@RequestBody RegistrationReqDto registrationReqDto);

}
