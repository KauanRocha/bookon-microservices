package br.com.bookon.user.payloads.response;

public class SimplesUserResponse {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SimplesUserResponse(Integer id) {
        this.id = id;
    }

    public SimplesUserResponse() {
    }
}
