package org.food.service;

import org.food.api.repository.Dao;
import org.food.api.service.MealService;
import org.food.dao.MealRepositoryImpl;
import org.food.dto.MealDto;
import org.food.model.Meal;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

        private ModelMapper modelMapper;

        private Dao<Meal> mealDao;

        @Autowired
        public MealServiceImpl(ModelMapper modelMapper, MealRepositoryImpl mealRepository){
                this.modelMapper = modelMapper;
                this.mealDao = mealRepository;
        }

        @Override
        @Transactional
        public List<MealDto> getAll(){

                Type listType = new TypeToken<List<MealDto>>(){}.getType();
                List<MealDto> mealsDtoList = modelMapper.map(mealDao.findAll(),listType);
                return mealsDtoList;
        }

        @Override
        @Transactional
        public void addMeal(MealDto mealDto){

                mealDao.create(new Meal(mealDto.getName(), mealDto.getPrice(), mealDto.getTime()));
        }

        @Override
        @Transactional
        public MealDto getMeal(MealDto mealDto){

                return modelMapper.map(mealDao.findById(mealDto.getId()), MealDto.class);
        }

        @Override
        public void deleteMealById(MealDto mealDto) {

                mealDao.delete(modelMapper.map(mealDto, Meal.class));
        }


        @Override
        @Transactional
        public void update(MealDto mealDto) {
                mealDao.update(modelMapper.map(mealDto, Meal.class));
        }



}
