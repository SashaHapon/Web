package org.food.controller;

import org.food.api.service.MealService;
import org.food.dto.MealDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class MealController {

    private MealService mealService;

    private ObjectMapper objectMapper;

    @Autowired
    public MealController(MealService mealService, ObjectMapper objectMapper){

        this.mealService = mealService;
        this.objectMapper = objectMapper;
    }

    public List<MealDto> getAll(){

        List<MealDto> mealDtoList = mealService.getAll();
        return mealDtoList;
    };

    public MealDto addMeal(String json) throws JsonProcessingException, IOException {
        MealDto mealDto = objectMapper.readValue(json, MealDto.class);
        mealService.addMeal(mealDto);
        return mealDto;
    };

    public MealDto getMeal(String json) throws JsonProcessingException, IOException {
        MealDto mealDto = objectMapper.readValue(json, MealDto.class);
        mealService.getMeal(mealDto);
        return mealDto;
    };

    public void deleteMealById(String json) throws JsonProcessingException, IOException {
        MealDto mealDto = objectMapper.readValue(json, MealDto.class);
        mealService.deleteMealById(mealDto);
    };

    void update(String json) throws JsonProcessingException, IOException {

        MealDto mealDto = objectMapper.readValue(json, MealDto.class);
        mealService.update(mealDto);
    };
}
