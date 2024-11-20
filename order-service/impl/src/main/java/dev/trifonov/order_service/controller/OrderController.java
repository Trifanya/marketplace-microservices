package dev.trifonov.order_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderInfo(@PathVariable("orderId") int orderId) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getOrdersByIds(List<Integer> ids) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> placeAnOrder(PlaceAnOrderRequest request) {
        return null;
    }
}
