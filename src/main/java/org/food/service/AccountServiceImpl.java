package org.food.service;

import org.food.api.repository.Dao;
import org.food.api.service.AccountService;
import org.food.dao.AccountRepositoryImpl;
import org.food.dto.AccountDto;
import org.food.model.Account;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;


@Service
public class AccountServiceImpl implements AccountService {

    private ModelMapper modelMapper;

    private Dao accountRepository;

    @Autowired
    public AccountServiceImpl(ModelMapper modelMapper, AccountRepositoryImpl accountRepository) {

        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public List<AccountDto> getAllAccounts() {

        Type listType = new TypeToken<List<AccountDto>>() {}.getType();

        List<AccountDto> accountsDtoList = modelMapper.map(accountRepository.findAll(), listType);
        return accountsDtoList;
    }

    @Override
    @Transactional

    public AccountDto addAccount(AccountDto accountDTO) {
        Account account = new Account(accountDTO.getName(), accountDTO.getMoney(), accountDTO.getPhoneNumber());
        accountRepository.create(account);
        return accountDTO;
    }

    @Override
    @Transactional
    public AccountDto getAccount(int id) {

        return modelMapper.map(accountRepository.findById(id), AccountDto.class);
    }

    @Override
    @Transactional
    public void deleteAccountById(int id) {

        accountRepository.delete(id);
    }

    @Override
    @Transactional
    public void update(AccountDto accountDTO) {

        accountRepository.update(modelMapper.map(accountDTO, Account.class));
    }
//
//    @Secured({ "ROLE_VIEWER", "ROLE_EDITOR" })
//    public boolean isValidUsername(String username) {
//        return userRoleRepository.isValidUsername(username);
//    }
}
