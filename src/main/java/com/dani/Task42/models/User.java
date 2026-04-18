package com.dani.Task42.models;

import lombok.Getter;

import java.util.UUID;


@Getter
public class User {


    private UUID id;
    private String name;
    private String email;

    public User() {
    }

    public User(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


}
