package org.food.api.service;
import org.food.dto.AccountDto ;

import java.util.List;


public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto addAccount(AccountDto accountDTO);

    AccountDto getAccount(Integer id);

    void deleteAccountById(Integer id);

    void update(AccountDto accountDTO);
 }
