package com.josemeurer.springBoot_mongoDB.dtos;

import com.josemeurer.springBoot_mongoDB.entities.Comment;
import com.josemeurer.springBoot_mongoDB.entities.UserAuthor;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class CommentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private Instant date;
    private String body;
    private UserAuthor author;

    public CommentDTO() {
    }

    public CommentDTO(Comment obj) {
        this.id = obj.getId();
        this.date = obj.getDate();
        this.body = obj.getBody();
        this.author = obj.getAuthor();
    }

    public CommentDTO(String id, Instant date, String body, UserAuthor author) {
        this.id = id;
        this.date = date;
        this.body = body;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public UserAuthor getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthor author) {
        this.author = author;
    }
}
