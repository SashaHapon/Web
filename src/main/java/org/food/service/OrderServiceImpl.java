package org.food.service;

import lombok.RequiredArgsConstructor;
import org.food.api.repository.AccountRepository;
import org.food.api.repository.MealRepository;
import org.food.api.repository.OrderRepository;
import org.food.api.service.OrderService;
import org.food.dto.MealDto;
import org.food.dto.OrderDto;
import org.food.model.Meal;
import org.food.model.Order;
import org.food.exception.classes.MyException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ModelMapper modelMapper;

    private final OrderRepository orderRepository;

    private final AccountRepository accountRepository;

    private final MealRepository mealRepository;

    // TODO: 24.11.2023 param ID
    @Override
    public void createOrder(Integer accountId, List<MealDto> mealDtoList) {

            Order order = new Order();
            order.setAccount(accountRepository.findById(accountId));
            Type listType = new TypeToken<List<Meal>>() {}.getType();
            List<Meal> meals = modelMapper.map(mealDtoList, listType);
            order.setMeals(meals);
            order.
            orderRepository.create(order);
    }

    @Override
    public OrderDto getOrder(OrderDto orderDto) {

        return modelMapper.map(orderRepository.findById(orderDto.getId()), OrderDto.class);
    }

    public void addMeals(Integer orderId, Integer[] mealsId) {

        Order order = orderRepository.findById(orderId);
        List<Meal> meals = orderRepository.findById(orderId).getMeals();

        for (int mealId : mealsId) {

            meals.add(mealRepository.findById(mealId));
        }

        order.setMeals(meals);
        orderRepository.update(order);
    }

    public void removeMeals(Integer orderId, Integer[] mealsId) {

        Order order = orderRepository.findById(orderId);
        List<Meal> meals = orderRepository.findById(orderId).getMeals();

        for (int mealId : mealsId) {

            meals.remove(mealRepository.findById(mealId));
        }

        order.setMeals(meals);
        orderRepository.update(order);
    }

    // TODO: 24.11.2023 get order with meal use entityGraph/ fetchJoin
    @Override
    public List<MealDto> getAllMeals(Integer orderId) {

        Type listType = new TypeToken<List<MealDto>>(){}.getType();
        return modelMapper.map(orderRepository.findById(orderId), listType);
    }

    // TODO: 24.11.2023 like getAllMeals
    private int cookingTimeSum(List<Meal> mealList) {

        Order order = orderRepository.findById(orderDto.getId());
        int cookingTimeSum = 0;
        for (Meal meal : order.getMeals()) {
            cookingTimeSum += meal.getTime();
        }
        order.setCookingTimeSum(cookingTimeSum);
        return cookingTimeSum;
    }

    // TODO: 24.11.2023 like getAllMeals
    @Transactional
    private double orderSum(OrderDto orderDto) {

        Order order = (Order) orderRepository.findById(orderDto.getId());

        double orderSum = 0;
        for (int i = 0; i < order.getMeals().size(); i++) {
            orderSum += order.getMeals().get(i).getPrice();
        }
        order.setOrderSum(orderSum);
        return orderSum;
    }

    // TODO: 24.11.2023  ?
    @Transactional
    @Override
    public double applyDiscount(OrderDto orderDto) {

        double discountPrise = (double) orderRepository.findById(orderDto.getId());
        discountPrise *= 84.4;
        orderRepository.findById(orderDto.getId());
        return discountPrise;
    }

    // TODO: 24.11.2023  ?
    @Transactional
    @Override
    public void checkPayment(OrderDto orderDto) throws MyException {

        if (orderRepository.findById(orderDto.getId()) == orderRepository.findById(orderDto.getId()))

            throw new MyException("Not enought money");
    }

}
