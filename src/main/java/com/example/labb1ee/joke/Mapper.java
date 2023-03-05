package com.example.labb1ee.joke;

import java.util.List;
import java.util.Optional;

public class Mapper {
    List<JokeDto> entityListToDtoList(List<Joke> jokes) {
        return jokes.stream().map(joke->new JokeDto(joke.getId(), joke.getJoke())).toList();
    }
    Optional<JokeDto> entityToDto(Optional<Joke> joke){
        return Optional.ofNullable(new JokeDto(joke.get().getId(), joke.get().getJoke()));
    }
}
