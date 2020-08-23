package com.udacity.graphql.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.udacity.graphql.entity.Dawg;
import com.udacity.graphql.repository.DawgRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {


    private DawgRepository dawgRepository;

    public Mutation(DawgRepository dawgRepository) {
        this.dawgRepository = dawgRepository;
    }

    public Dawg newDawg(String name, String breed, String origin) {
        Dawg dawg = new Dawg(name, breed, origin);
        dawgRepository.save(dawg);
        return dawg;
    }



    public boolean deleteDawgBreed(String breed) {
        boolean deleted = false;
        Iterable<Dawg> allDawgs = dawgRepository.findAll();

        // Loop through all dogs to check their breed
        for (Dawg d:allDawgs) {
            if (d.getBreed().equals(breed)) {
                dawgRepository.delete(d);
                deleted = true;
            }
        }

        /*
        // Throw an exception if the breed doesn't exist
        if (!deleted) {
            throw new BreedNotFoundException("Breed Not Found", breed);
        }

         */

        return deleted;
    }

    public Dawg updateDawgName(String newName, Long id) {

        Optional<Dawg> optionalDawg = dawgRepository.findById(id);

        Dawg dawg = optionalDawg.get();
        // Set the new name and save the updated dog
        dawg.setName(newName);
        dawgRepository.save(dawg);
        return dawg;

        /*
        if (optionalDawg.isPresent()) {
            Dawg dawg = optionalDawg.get();
            // Set the new name and save the updated dog
            dawg.setName(newName);
            dawgRepository.save(dawg);
            return dawg;
        } else {
            throw new DogNotFoundException("Dog Not Found", id);
        }

         */
    }





}


