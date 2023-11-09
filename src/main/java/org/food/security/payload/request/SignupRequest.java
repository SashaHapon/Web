package org.food.security.payload.request;

import lombok.Data;
import org.food.security.model.Role;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class SignupRequest {

    private String username;
    private String email;
    private  String password;
    private Set<String> role = new HashSet<>();
}
