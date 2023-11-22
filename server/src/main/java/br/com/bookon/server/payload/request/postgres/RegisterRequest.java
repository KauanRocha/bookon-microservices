package br.com.bookon.server.payload.request.postgres;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
	
    @NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    private String username;

    @NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    @Size(min = 6, max = 40, message="{fieldName}-must-have-between-{min}-and-{max}-characters")
    private String password;

    @NotBlank(message="{fieldName}-is-mandatory")
    @NotNull
    @Email
    private String email;

    private Set<String> role;
    
    private Double longitude; 
    
    private Double latitude;
    
    private String address;
    
    private boolean geolocationFromNatigator;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGeolocationFromNatigator() {
		return geolocationFromNatigator;
	}

	public void setGeolocationFromNatigator(boolean geolocationFromNatigator) {
		this.geolocationFromNatigator = geolocationFromNatigator;
	}

}
