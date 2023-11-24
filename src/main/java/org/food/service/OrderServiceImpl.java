package org.food.service;

import org.food.api.repository.GenericDao;
import org.food.api.service.OrderService;
import org.food.dao.OrderRepositoryImpl;
import org.food.dto.MealDto;
import org.food.dto.OrderDto;
import org.food.model.Account;
import org.food.model.Meal;
import org.food.model.Order;
import org.food.utils.MyException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ModelMapper modelMapper;

    private GenericDao abstractDao;

    @Autowired
    public OrderServiceImpl(ModelMapper modelMapper, OrderRepositoryImpl orderRepository) {

        this.modelMapper = modelMapper;
        this.abstractDao = orderRepository;
    }


    @Override
    @Transactional
    public void createOrder(OrderDto orderDto) {

            Order order = new Order();
            order.setAccount(modelMapper.map(orderDto.getAccountDto(), Account.class));
            order.setMeals(orderDto.getMeals());
            abstractDao.create(order);
    }

    @Override
    @Transactional
    public OrderDto getOrder(OrderDto orderDto) {

        return modelMapper.map(abstractDao.findById(orderDto.getId()), OrderDto.class);
    }

    @Override
    @Transactional
    public void addMeal(OrderDto orderDto) {

        Meal meal = modelMapper.map(orderDto.getMealDto(), Meal.class);
        Order order = (Order) abstractDao.findById(orderDto.getId());

        order.getMeals().add(meal);
        order.setOrderSum(order.getOrderSum() + meal.getPrice());
        order.setCookingTimeSum(order.getCookingTimeSum() + meal.getTime());
        abstractDao.update(order);
    }

    @Override
    @Transactional
    public void removeMeal(OrderDto orderDto) {

        Meal meal = modelMapper.map(orderDto.getMealDto(), Meal.class);
        Order order = (Order) abstractDao.findById(orderDto.getId());

        order.getMeals().remove(meal);
        order.setCookingTimeSum(order.getCookingTimeSum() - meal.getTime());
        abstractDao.update(order);
    }

    @Override
    @Transactional
    public List<MealDto> getAllMeals(OrderDto orderDto) {

        Type listType = new TypeToken<List<MealDto>>(){}.getType();
        List<MealDto> mealsDtoList = modelMapper.map(abstractDao.findById(orderDto.getId()), listType);
        return mealsDtoList;
    }

    @Transactional
    private int cookingTimeSum(OrderDto orderDto) {

        Order order = (Order) abstractDao.findById(orderDto.getId());
        int cookingTimeSum = 0;
        for (Meal meal : order.getMeals()) {
            cookingTimeSum += meal.getTime();
        }
        order.setCookingTimeSum(cookingTimeSum);
        return cookingTimeSum;
    }

    @Transactional
    private double orderSum(OrderDto orderDto) {

        Order order = (Order) abstractDao.findById(orderDto.getId());

        double orderSum = 0;
        for (int i = 0; i < order.getMeals().size(); i++) {
            orderSum += order.getMeals().get(i).getPrice();
        }
        order.setOrderSum(orderSum);
        return orderSum;
    }

    @Transactional
    @Override
    public double applyDiscount(OrderDto orderDto) {

        double discountPrise = (double) abstractDao.findById(orderDto.getId());
        discountPrise *= 84.4;
        abstractDao.findById(orderDto.getId());
        return discountPrise;
    }

    @Transactional
    @Override
    public void checkPayment(OrderDto orderDto) throws MyException {

        if (abstractDao.findById(orderDto.getId()) == abstractDao.findById(orderDto.getId()))

            throw new MyException("Not enought money");
    }

}
