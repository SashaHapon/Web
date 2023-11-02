package org.food.api.service;

import java.util.UUID;

public interface WalletService {
    double enrollmentMoney(String id, double enrollmentMoney);
    double writeOffMoneyOnCard(String id, double writeOffMoney);
}
