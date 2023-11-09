package org.food.security.payload.response;

import lombok.Data;
import org.food.security.model.Role;

import java.util.List;

@Data
public class UserInfoResponse {

    private long id;

    String username;

    String email;

    List<String> role;

    public UserInfoResponse(long id, String username, String email, List<String> role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
