package org.food.controller;

import org.food.api.service.AccountService;
import org.food.dto.AccountDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.food.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {


    private AccountService accountService;

    private ObjectMapper objectMapper;


    @Autowired
    public AccountController(AccountServiceImpl accountService, ObjectMapper objectMapper){
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @GetMapping("/")
    public List<AccountDto> getAllAccounts(@RequestParam(defaultValue = "3") int i) {

        // TODO: 18.10.2023 пагинация
        List<AccountDto> accounts = accountService.getAllAccounts();
        return accounts;
    }

    @PostMapping("/")
    public AccountDto addAccount(@RequestBody AccountDto accountDto){

        return accountService.addAccount(accountDto);
    }

    @GetMapping({"/{id}"})
    @ResponseBody
    public AccountDto getAccount(@PathVariable("id") int id){

        return accountService.getAccount(id);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void deleteAccountById(@PathVariable("id") int id){

        accountService.deleteAccountById(id);
    }

    @PutMapping("/{Id}")
    void update(@RequestBody AccountDto accountDto){

        accountService.update(accountDto);
    }
}
