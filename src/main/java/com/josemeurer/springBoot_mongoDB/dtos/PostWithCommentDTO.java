package com.josemeurer.springBoot_mongoDB.dtos;

import com.josemeurer.springBoot_mongoDB.entities.Post;
import com.josemeurer.springBoot_mongoDB.entities.UserAuthor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostWithCommentDTO {

    private String id;
    private Instant date;
    private String title;
    private String body;
    private UserAuthor author;

    List<CommentDTO> commentDTOS = new ArrayList<>();

    public PostWithCommentDTO() {
    }

    public PostWithCommentDTO(Post obj) {
        this.id = obj.getId();
        this.date = obj.getDate();
        this.title = obj.getTitle();
        this.body = obj.getBody();
        this.author = obj.getAuthor();
        this.commentDTOS = obj.getComments().stream().map(CommentDTO::new).toList();
    }

    public PostWithCommentDTO(String id, Instant date, String title, String body, UserAuthor author, List<CommentDTO> commentDTOS) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.author = author;
        this.commentDTOS = commentDTOS;
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

    public UserAuthor getAuthor() {
        return author;
    }

    public void setAuthor(UserAuthor author) {
        this.author = author;
    }

    public List<CommentDTO> getCommentDTOS() {
        return commentDTOS;
    }

    public void setCommentDTOS(List<CommentDTO> commentDTOS) {
        this.commentDTOS = commentDTOS;
    }
}
