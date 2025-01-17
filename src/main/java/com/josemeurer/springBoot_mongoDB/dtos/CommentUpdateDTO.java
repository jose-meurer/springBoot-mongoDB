package com.josemeurer.springBoot_mongoDB.dtos;

import java.io.Serial;
import java.io.Serializable;

public class CommentUpdateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

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
