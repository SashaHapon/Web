package org.food.security.payload.request;

import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
public class SignupRequest {

    private String username;
    private String email;
    private  String password;
    private Set<String> role = new HashSet<>();
}
