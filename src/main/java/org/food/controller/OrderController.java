package org.food.controller;

import org.food.api.service.OrderService;
import org.food.dto.MealDto;
import org.food.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.food.exception.classes.MyException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

// TODO: 24.11.2023
public class OrderController {

    OrderService orderService;

    ObjectMapper objectMapper;

    @Autowired
    public OrderController(OrderService orderService, ObjectMapper objectMapper){

        this.orderService = orderService;
        this.objectMapper = objectMapper;
    }

    public OrderDto createOrder(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.createOrder(orderDto);
        return orderDto;
    }

    public OrderDto getOrder(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.getOrder(orderDto);
        return orderDto;
    }

    public void addMeal(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.addMeal(orderDto);
    }

    public void remove(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.removeMeal(orderDto);
    };

    public List<MealDto> getAllMeals(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        return orderService.getAllMeals(orderDto);
    };

    public void applyDiscount(String json) throws JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.applyDiscount(orderDto);
    }

    public void checkPayment(String json) throws MyException, JsonProcessingException {

        OrderDto orderDto = objectMapper.readValue(json, OrderDto.class);
        orderService.applyDiscount(orderDto);

    }
}
