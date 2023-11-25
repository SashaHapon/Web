package org.food.controller;

import lombok.RequiredArgsConstructor;
import org.food.api.service.AccountService;
import org.food.dto.AccountDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/")
    public List<AccountDto> getAllAccounts(@RequestParam(defaultValue = "3", required = false) int limit,
                                           @RequestParam(required = false) int offset) {

        // TODO: 18.10.2023 пагинация
        return accountService.getAllAccounts();
    }

    @PostMapping("/")
    public AccountDto addAccount(@RequestBody AccountDto accountDto) {

        return accountService.addAccount(accountDto);
    }

    @GetMapping("/{id}")
    public AccountDto getAccount(@PathVariable("id") Integer id) {

        return accountService.getAccount(id);
    }

    @DeleteMapping("/{id}")
    void deleteAccountById(@PathVariable("id") Integer id) {

        accountService.deleteAccountById(id);
    }

    @PutMapping("/{id}")
    void update(@RequestBody AccountDto accountDto) {

        accountService.update(accountDto);
    }
}
