package com.petstore.data.repository;

import com.petstore.data.model.Store;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Sql(scripts = {"classpath:db/insert.sql"})
class StoreRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Autowired
    StoreRepository storeRepository;

    @BeforeEach
    void setUp() {
    }

    //Test that we can add store to db
    @Test
    public void whenNewStoreIsAdded_thenReturnNewStore() {
        //Create a new store
        Store store = new Store();
        store.setName("Pet Sellers annex");
        store.setLocation("Abuja");
        store.setContactNo("08157646321");
        storeRepository.save(store);

        log.info("Store instance after saving --> {}", store);

        //assert to make sure store is available
        assertThat(store.getId()).isNotNull();
    }

    @Test
    @Transactional
    public void whenIUpdateAStore_thenReturnUpdatedStore() {
        //fetch a store
        Store superstore3 = storeRepository.findById(23).orElse(null);

        //assert the field
        assertThat(superstore3).isNotNull();
        assertThat(superstore3.getLocation()).isEqualTo("Lagos");

        //assert store field
        superstore3.setLocation("Ibadan");

        //save change
        storeRepository.save(superstore3);
        log.info("After updating store object --> {}", superstore3);

        //assert that updated field has changed
        assertThat(superstore3.getLocation()).isEqualTo("Ibadan");
    }

    @Test
    public void whenIdeleteStoreFromDatabase_thenStoreIsDeleted() {
        //check if store exist
        boolean result = storeRepository.existsById(24);

        //assert that store exists
        assertThat(result).isTrue();

        //delete store
        storeRepository.deleteById(24);

        //check if store still exists
        assertThat(storeRepository.existsById(24)).isFalse();
    }

    public void findAllPetsInAllStoreTest() {
        Store store = storeRepository.findById(21).orElse(null);
        assertThat(store).isNotNull();
        assertThat(store.getPetList()).isNotNull();
        log.info("Pets in the store of ID 21 --> {}", store.getPetList());
    }

    @Test
    void findStoreByNameTest() {
        Store store = storeRepository.findByName("superstore2");
        log.info("Store object --> {}", store);
        assertThat(store).isNotNull();
    }
}
