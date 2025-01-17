package com.josemeurer.springBoot_mongoDB.dtos;

import java.io.Serializable;

public class PostUpdateDTO implements Serializable {

    private String title;
    private String body;

    public PostUpdateDTO() {
    }

    public PostUpdateDTO(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
