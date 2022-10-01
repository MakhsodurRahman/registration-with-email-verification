package com.rwev.registration;

import org.springframework.transaction.annotation.Transactional;

public interface RegistrationService {

    String register(RegistrationReqDto registrationReqDto);
    @Transactional
    public String confirmToken(String token);
}
