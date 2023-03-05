package com.example.labb1ee.joke;

import com.example.labb1ee.exception.IdNotFoundException;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/jokes")
public class JokeController {
    @Inject JokeRepository jokeRepository;

    @Inject Mapper mapper;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JokeDto> getAllJoke(@QueryParam("name") String name){
        if(name == null)
            return mapper.entityListToDtoList(jokeRepository.findAll());
        return mapper.entityListToDtoList(jokeRepository.findAllByName(name));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Optional<JokeDto> getJokeById(@PathParam("id") Long id){
        if (jokeRepository.findOne(id).isPresent()) {
            return mapper.entityToDto(jokeRepository.findOne(id));
        }
        throw new IdNotFoundException("id "+ id +" not found");
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createJoke(@Valid Joke joke){
        jokeRepository.insertJoke(joke);
        return Response.created(URI.create("jokes/"+ joke.getId())).build();
    }

    @Path("/{id}")
    @DELETE
    public void deleteJoke(@PathParam("id") Long id) {
        jokeRepository.deleteJoke(id);
    }
}
