package dev.trifonov.order_service.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Profile()
@RestController
@RequestMapping("/orders")
public class OrderController {
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderInfo(@PathVariable("orderId") int orderId) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> getUserOrders(List<Integer> ids) {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> placeAnOrder(PlaceAnOrderRequest request) {
        return null;
    }
}
