package com.shopsmart.inventoryservice.repository;

import com.shopsmart.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Hum multiple SKUs check karenge ek sath
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}