package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import java.util.List;


public interface PetService {

    Pet savePet(Pet pet);
    Pet updatePet(Pet pet);
    Pet findPetById(Integer Id);
    List<Pet> findAllPats();
    void deletePetById(Integer Id);

}
