package org.food.controller;

import lombok.RequiredArgsConstructor;
import org.food.api.service.OrderService;
import org.food.dto.MealDto;
import org.food.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{id}")
    public void createOrder(@RequestParam("id") Integer accountId,
                            @RequestBody List<MealDto> mealDtoList) {

        orderService.createOrder(accountId, mealDtoList);
    }

    @GetMapping("/{id}")
    public OrderDto getOrder(@RequestParam("id") Integer id) {

        return orderService.getOrder(id);
    }

    @PutMapping("/{id}")
    public void addMeals(@RequestParam("id") Integer orderId, @RequestBody Integer[] mealsId) {

        orderService.addMeals(orderId, mealsId);
    }

    @PutMapping("/{id}")
    public void removeMeals(@RequestParam("id") Integer orderId,
                            @RequestBody Integer[] mealsId) {

        orderService.removeMeals(orderId, mealsId);
    }

    @GetMapping("/{id}")
    public List<MealDto> getAllMeals(@RequestParam("id") Integer id) {

        return orderService.getAllMeals(id);
    }
}
