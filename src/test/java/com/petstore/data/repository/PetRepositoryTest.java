package com.petstore.data.repository;

import com.petstore.data.model.Gender;
import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.petstore.data.model.Pet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    //Test that we can save a pet to the database
    @Test
    public void whenPetIsSaved_thenReturnPetId() {

        //Step 1: Create an instance of a pet
        Pet pet = new Pet();
        pet.setName("Puffy");
        pet.setAge(3);
        pet.setBreed("Dog");
        pet.setColor("Ash");
        pet.setPetSex(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);

        //Call repository save method
        petRepository.save(pet);

        assertThat(pet.getId()).isNotNull();
        log.info("Pet instance before saving --> {}", pet);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenStoreIsMappedToPet_thenForeignKeyIsPresent(){
        //Create a pet
        Pet pet = new Pet();
        pet.setName("Puffy");
        pet.setAge(3);
        pet.setBreed("Dog");
        pet.setColor("Ash");
        pet.setPetSex(Gender.MALE);

        log.info("Pet instance before saving --> {}", pet);

        //Create a store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("07034515037");

        //Map pet to store
        pet.setStore(store);

        //save pet
        petRepository.save(pet);

        //assert
        assertThat(pet.getId()).isNotNull();
        assertThat(store.getId()).isNotNull();
        assertThat(pet.getStore()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void whenIAddPetsToStore_thenICanFetchAListOfPetsFromStore(){
        //Create a store
        Store store = new Store();
        store.setName("Pet Sellers");
        store.setLocation("Yaba");
        store.setContactNo("07034515037");

        //Create a pet
        Pet sally = new Pet();
        sally.setName("sally");
        sally.setAge(2);
        sally.setBreed("Dog");
        sally.setColor("Brown");
        sally.setPetSex(Gender.FEMALE);
        sally.setStore(store);

        Pet puffy = new Pet();
        puffy.setName("puffy");
        puffy.setAge(3);
        puffy.setBreed("Dog");
        puffy.setColor("Ash");
        puffy.setPetSex(Gender.MALE);
        puffy.setStore(store);

        log.info("Pet instance before saving --> {}", puffy, sally);

        //Map store to pet
        store.addPets(sally);
        store.addPets(puffy);

        //save store
        log.info("Store instance after saving --> {}", store);

        //save pet
        petRepository.save(sally);
        petRepository.save(puffy);

        //assert for pet Id
        assertThat(puffy.getId()).isNotNull();
        assertThat(sally.getId()).isNotNull();

        //assert store
        assertThat(store.getId()).isNotNull();

        //assert to make sure store has pets
        assertThat(store.getPetList()).isNotEmpty();
    }

    @Test
    public void whenFindAllPetIsCalled_thenReturnAllPetsInStore(){
        //find pets from store
        List<Pet> savedPets = petRepository.findAll();

        log.info("fetched pets list from db --> {}", savedPets);
        //assert that pets exists
        assertThat(savedPets).isNotEmpty();
        assertThat(savedPets.size()).isEqualTo(6);
    }

    @Test
    public void updateExistingPetDetailsTest(){
        //fetch a pet
        Pet prissy = petRepository.findById(24).orElse(null);

        //assert the field
        assertThat(prissy).isNotNull();
        assertThat(prissy.getColor()).isEqualTo("black");

        //update pet field
        prissy.setColor("gold");

        //save change
        petRepository.save(prissy);
        log.info("After updating pet object --> ()", prissy);

        //assert that updated field has changed
        assertThat(prissy.getColor()).isEqualTo("gold");

    }

    @Test
    public void whenIdeletePetFromDatabase_thenPetIsDeleted(){
        //check if pet exist
        boolean result = petRepository.existsById(28);

        //assert that pet exists
        assertThat(result).isTrue();

        //delete pet
        petRepository.deleteById(28);

        //check if pet exists
        assertThat(petRepository.existsById(28)).isFalse();
    }
}