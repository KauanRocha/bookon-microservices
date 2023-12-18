package br.com.bookon.user.payloads.response;

public class UserResponse {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserResponse() {
    }

    public UserResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
