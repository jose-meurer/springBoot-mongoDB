package com.josemeurer.springBoot_mongoDB.dtos;

import java.io.Serial;
import java.io.Serializable;

public class CommentInsertDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;


    private String body;
    private String userId;

    public CommentInsertDTO() {
    }

    public CommentInsertDTO(String body, String userId) {
        this.body = body;
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
