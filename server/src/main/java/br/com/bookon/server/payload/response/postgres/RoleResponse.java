package br.com.bookon.server.payload.response.postgres;

import br.com.bookon.server.enumerations.RoleEnum;
import br.com.bookon.server.models.postgres.Role;

public class RoleResponse {

    private Role role;

    public RoleResponse(Role role) {
        this.role = role;
    }

    public RoleEnum getName() {
        return role.getName();
    }

}
