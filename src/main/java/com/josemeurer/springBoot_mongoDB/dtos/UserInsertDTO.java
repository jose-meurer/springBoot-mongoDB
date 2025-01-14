package com.josemeurer.springBoot_mongoDB.dtos;

public class UserInsertDTO {

    private String name;
    private String email;

    public UserInsertDTO() {
    }

    public UserInsertDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
