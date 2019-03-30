package com.jumia.jumiapay.persistence.repository;

import com.jumia.jumiapay.persistence.models.BankCard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
public interface BankCardRepository extends CrudRepository<BankCard, Long> {

    Optional<BankCard> findBankCardByCardNumber(String cardNumber);
}
