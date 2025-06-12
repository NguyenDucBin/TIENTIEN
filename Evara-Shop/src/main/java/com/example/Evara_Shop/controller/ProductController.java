package com.example.Evara_Shop.controller;

import com.example.Evara_Shop.DTO.product.ProductCreateDTO;
import com.example.Evara_Shop.DTO.product.ProductDTO;
import com.example.Evara_Shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductCreateDTO dto) {
        return ResponseEntity.ok(productService.create(dto));
    }
}

