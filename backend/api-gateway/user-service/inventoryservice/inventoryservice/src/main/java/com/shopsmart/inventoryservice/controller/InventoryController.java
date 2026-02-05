package com.shopsmart.inventoryservice.controller;

import com.shopsmart.inventoryservice.dto.InventoryResponse;
import com.shopsmart.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // URL aisa dikhega: http://localhost:8084/api/inventory?skuCode=iphone_15&skuCode=pixel_8
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}