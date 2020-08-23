package com.udacity.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.udacity.graphql.entity.Dawg;
import com.udacity.graphql.repository.DawgRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    private DawgRepository dawgRepository;

    public Query(DawgRepository dawgRepository) {
        this.dawgRepository = dawgRepository;
    }

    public Iterable<Dawg> findAllDawgs() {
        return dawgRepository.findAll();
    }



    public Dawg findDawgById(Long id) {
        Optional<Dawg> optionalDawg = dawgRepository.findById(id);
        return optionalDawg.get();

        /*
        if (optionalDawg.isPresent()) {
            return optionalDawg.get();
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }

         */
    }
}
