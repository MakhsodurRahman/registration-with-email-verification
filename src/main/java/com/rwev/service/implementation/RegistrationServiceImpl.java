package com.rwev.service.implementation;

import com.rwev.dto.RegistrationReqDto;
import com.rwev.repository.AppUserRepository;
import com.rwev.service.definition.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final AppUserRepository appUserRepository;
    @Override
    public String register(RegistrationReqDto registrationReqDto) {


        return "work";
    }
}
