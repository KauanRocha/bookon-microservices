package br.com.bookon.book.payloads.response;

public class SimpleUserResponse {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SimpleUserResponse(Integer id) {
        this.id = id;
    }

    public SimpleUserResponse() {
    }
}