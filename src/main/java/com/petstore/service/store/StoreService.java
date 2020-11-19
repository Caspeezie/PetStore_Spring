package com.petstore.service.store;

import com.petstore.data.model.Store;

import java.util.List;

public interface StoreService {
    Store saveStore(Store store);
    Store updateStore(Store store);
    Store findStoreById(Store store);
    void deleteStoreById(Integer Id);
    List<Store> findAllStores();
}
