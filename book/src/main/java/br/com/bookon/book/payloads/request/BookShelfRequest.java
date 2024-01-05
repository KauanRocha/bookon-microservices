package br.com.bookon.book.payloads.request;

import jakarta.validation.constraints.NotNull;

public class BookShelfRequest {

    @NotNull
    private String name;

    @NotNull
    private Integer userId;
    private Double latitude;

    private Double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public BookShelfRequest() {
    }
    public BookShelfRequest(String name, Integer userId, Double latitude, Double longitude) {
        this.name = name;
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
