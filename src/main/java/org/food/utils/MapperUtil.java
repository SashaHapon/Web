package org.food.utils;

import org.food.dto.AccountDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.food.model.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class MapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ModelMapper modelMapper = new ModelMapper();

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }

    public static AccountDto convertAccountToDTO(Account convertedObject) {
        AccountDto accountDTO = modelMapper.map(convertedObject, AccountDto.class);
        return accountDTO;
    }

    public static List<AccountDto> convertToAccountDTOList(List<Account> accounts) {

        Type listType = new TypeToken<List<AccountDto>>(){}.getType();
        List<AccountDto> postDtoList = modelMapper.map(accounts,listType);
        return postDtoList;
    }


    public static Object convertDtoToObject(Object convertedObject, Class classToConvert) {
        Object objectDto = modelMapper.map(convertedObject, classToConvert);
        return objectDto;
    }

    public static String pojoToJsonString(Object object) throws JsonProcessingException {

        String json = objectMapper.writeValueAsString(object);
        System.out.println(json);
        return json;
    }

    public static AccountDto jsonStringToPojoAccountDTO(String json){

        try {

            AccountDto accountDTO = objectMapper.readValue(json, AccountDto.class);
            return accountDTO;

        } catch (JsonProcessingException e) {
              throw new RuntimeException(e.getMessage());
        }

    }
}