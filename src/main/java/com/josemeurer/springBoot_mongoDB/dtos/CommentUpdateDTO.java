package com.josemeurer.springBoot_mongoDB.dtos;

public class CommentUpdateDTO {

    private String body;

    public CommentUpdateDTO() {
    }

    public CommentUpdateDTO(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
