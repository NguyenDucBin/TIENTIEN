package com.example.Evara_Shop.controller;

import com.example.Evara_Shop.DTO.order.OrderCreateDTO;
import com.example.Evara_Shop.DTO.order.OrderDTO;
import com.example.Evara_Shop.DTO.order.OrderUpdateDTO;
import com.example.Evara_Shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderCreateDTO dto) {
        return ResponseEntity.ok(orderService.create(dto));
    }

    @PutMapping("/{id}/next")
    public ResponseEntity<OrderDTO> next(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.advanceState(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.cancel(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> update(
            @PathVariable Long id,
            @RequestBody OrderUpdateDTO dto
    ) {
        return ResponseEntity.ok(orderService.update(id, dto));
    }
}

