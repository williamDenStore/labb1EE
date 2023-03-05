package com.example.labb1ee.joke;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class JokeRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<Joke> findAll(){

        Query query = entityManager.createQuery("select j from Joke j");
        return (List<Joke>)query.getResultList();
    }
    public Optional<Joke> findOne(Long id){
        return Optional.ofNullable(entityManager.find(Joke.class, id));
    }
    public void insertJoke(Joke joke){
        entityManager.persist(joke);
    }
    public void deleteJoke(Long id){
        Optional<Joke> joke = findOne(id);
        joke.ifPresent((j) -> entityManager.remove(j));
    }

    public List<Joke> findAllByName(String name) {
        Query query = entityManager.createQuery("select j from Joke j where j.joke like :name");
        query.setParameter("name",name);
        return (List<Joke>)query.getResultList();
    }
}
