package com.petstore.service.store;

import com.petstore.data.model.Store;
import com.petstore.data.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StoreServiceImplTest {

    @Mock
    StoreRepository storeRepository;

    @InjectMocks
    StoreService storeService = new StoreServiceImpl();
    Store testStore;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testStore = new Store();
    }

    @Test
    void testThatICanSaveStoreTest() {
        when(storeRepository.save(testStore)).thenReturn(testStore);
        storeService.saveStore(testStore);
        verify(storeRepository, times(1)).save(testStore);
    }

//    @Test
//    void testThatICanUpdateStore() {
//        when(storeRepository.save(testStore)).thenReturn(testStore);
//        storeService.updateStore(testStore);
//        verify(storeRepository, times(1)).save(testStore);
//    }

    @Test
    void testThatICanFindAllstore() {
        List<Store> storeList = new ArrayList<>();

        when(storeRepository.findAll()).thenReturn(storeList);
        storeService.findAllStores();
        verify(storeRepository, times(1)).findAll();

    }

    @Test
    void testThatICanDeleteAstore() {
        doNothing().when(storeRepository).deleteById(2);
        storeService.deleteStoreById(2);
        verify(storeRepository, times(1)).deleteById(2);
    }

    @Test
    void testThatICanFindStoreById() {
        when(storeRepository.findById(2)).thenReturn(Optional.of(testStore));
        storeService.findStoreById(2);
        verify(storeRepository, times(1)).findById(2);
    }
}