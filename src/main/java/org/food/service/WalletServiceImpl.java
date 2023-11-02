package org.food.service;


import org.food.api.service.AccountService;
import org.food.api.service.WalletService;


public class WalletServiceImpl implements WalletService {

    private AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public double enrollmentMoney(String idAccount, double enrollmentMoney) {
     //   accountService.getAccount(idAccount).setMoney(accountService.getAccount(idAccount).getMoney() + enrollmentMoney);
        return enrollmentMoney;
    }

    @Override
    public double writeOffMoneyOnCard(String idAccount, double writeOffMoney) {
    //    accountService.getAccount(idAccount).setMoney(accountService.getAccount(idAccount).getMoney() - writeOffMoney);
        return writeOffMoney;
    }
}
