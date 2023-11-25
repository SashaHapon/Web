package org.food.controller;

import lombok.RequiredArgsConstructor;
import org.food.api.service.MealService;
import org.food.dto.MealDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: 24.11.2023 fix
@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    public List<MealDto> getAll(@RequestParam(defaultValue = "3", required = false) int limit,
                                @RequestParam(required = false) int offset) {

        // TODO: 25.11.2023 пагинация
        return mealService.getAll();
    }

    @PostMapping("/")
    public MealDto addMeal(MealDto mealDto) {

        return mealService.addMeal(mealDto);
    }

    @GetMapping("/{id}")
    public MealDto getMeal(@PathVariable("id") Integer id){

        return mealService.getMeal(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMealById(@PathVariable("id") Integer id){

        mealService.deleteMealById(id);
    }

    @PutMapping("/{id]")
    void update(@RequestBody MealDto mealDto){

        mealService.update(mealDto);
    }
}
