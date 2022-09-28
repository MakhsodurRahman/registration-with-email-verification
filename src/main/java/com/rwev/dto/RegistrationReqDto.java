package com.rwev.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationReqDto {
    public final String firstName;
    public final String lastName;
    public final String password;
    public final String email;
}
