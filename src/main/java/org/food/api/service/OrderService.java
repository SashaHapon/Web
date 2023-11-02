package org.food.api.service;
import org.food.dto.MealDto;
import org.food.dto.OrderDto;
import org.food.utils.MyException;

import java.util.List;

public interface OrderService {

    void createOrder(OrderDto orderDto);

    OrderDto getOrder(OrderDto orderDto);

    void addMeal(OrderDto orderDto);

    void removeMeal(OrderDto orderDto);

    List<MealDto> getAllMeals(OrderDto orderDto);

    double applyDiscount(OrderDto orderDto);

    void checkPayment(OrderDto orderDto) throws MyException;

}
