package com.petstore.service.pet;

import com.petstore.data.model.Pet;
import com.petstore.data.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class PetServiceImplTest {
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetService petService = new PetServiceImpl();

    Pet testPet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testPet = new Pet();
    }

    @Test
    void mockTheSavePetToRepositoryTest(){
        when(petRepository.save(testPet)).thenReturn(testPet);
        petService.savePet(testPet);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void mockTheFindByIdRepositoryTest(){
        when(petRepository.findById(2)).thenReturn(Optional.of(testPet));
        petService.findPetById(2);

        verify(petRepository, times(1)).save(testPet);
    }

    @Test
    void mockDeletePetRepositoryTest(){
        doNothing().when(petRepository).deleteById(2);
        petService.deletePetById(2);

        verify(petRepository, times(1)).deleteById(2);
    }
}