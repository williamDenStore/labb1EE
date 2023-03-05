package com.example.labb1ee.joke;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JokeDto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long id;
    @NotNull(message = "joke can't be null")
    @Size(min = 4, message = "joke can't be shorter than 4 characters")
    public String joke;

    public JokeDto(Long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
