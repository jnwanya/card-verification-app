package com.jumia.jumiapay.infrastructure.usecase;

import com.jumia.jumiapay.infrastructure.dto.BankCardDTO;
import com.jumia.jumiapay.web.pojo.response.BankCardStatResponse;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public interface CardManagementService {

    BankCardDTO resolveCardDetails(String cardNumber);

    BankCardStatResponse getBankCardStatistics(int start, int limit);
}
