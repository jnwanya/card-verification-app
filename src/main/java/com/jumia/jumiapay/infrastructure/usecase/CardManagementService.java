package com.jumia.jumiapay.infrastructure.usecase;

import com.jumia.jumiapay.infrastructure.dto.BankCardDTO;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public interface CardManagementService {

    BankCardDTO resolveCardDetails(String cardNumber);
}
