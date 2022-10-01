package com.rwev.registration;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
public interface UserRegistrationController {
    @PostMapping("/registration")
    public String register(@RequestBody RegistrationReqDto registrationReqDto);

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token);

}
