package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.web.exceptions.PetDoesNotExistException;

import java.util.List;


public interface PetService {

    Pet savePet(Pet pet);
    Pet updatePet(Pet pet) throws PetDoesNotExistException;
    Pet findPetById(Integer Id) throws PetDoesNotExistException;
    List<Pet> findAllPets();
    void deletePetById(Integer Id) throws PetDoesNotExistException;

}
