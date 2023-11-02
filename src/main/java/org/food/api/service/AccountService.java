package org.food.api.service;
import org.food.dto.AccountDto ;

import java.util.List;


public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto addAccount(AccountDto accountDTO);

    AccountDto getAccount(int id);

    void deleteAccountById(int id);

    void update(AccountDto accountDTO);
 }
