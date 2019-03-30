package com.jumia.jumiapay.persistence.service;

import com.jumia.jumiapay.persistence.models.BankCard;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public interface BankCardService extends CrudService<BankCard, Long> {

    Optional<BankCard> findBankCardByCardNumber(String cardNumber);

    Page<BankCard> getBankCards(int startIndex, int size);
}
