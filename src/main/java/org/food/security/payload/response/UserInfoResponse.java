package org.food.security.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.food.security.model.Role;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private List<String> role;
}
