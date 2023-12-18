package br.com.bookon.user.payloads.request;

import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
