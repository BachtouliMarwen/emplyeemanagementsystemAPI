package com.tekup.staffmanagementapi.auth;

import com.tekup.staffmanagementapi.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private Integer phone;
    private String email;
    private String password;
    private Role role;
}
