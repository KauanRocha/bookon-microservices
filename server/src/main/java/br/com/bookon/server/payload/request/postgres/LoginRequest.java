package br.com.bookon.server.payload.request.postgres;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
  	private String username;

	@NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    @Size(min = 6, max = 40, message="{fieldName}-must-have-between-{min}-and-{max}-characters")
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
