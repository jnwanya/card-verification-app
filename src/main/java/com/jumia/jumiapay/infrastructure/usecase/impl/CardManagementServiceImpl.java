package com.jumia.jumiapay.infrastructure.usecase.impl;

import com.jumia.jumiapay.infrastructure.dto.BankCardDTO;
import com.jumia.jumiapay.infrastructure.usecase.CardManagementService;
import com.jumia.jumiapay.persistence.models.BankCard;
import com.jumia.jumiapay.persistence.service.BankCardService;
import com.jumia.jumiapay.web.exceptions.BadRequestException;
import com.jumia.jumiapay.web.exceptions.NotFoundException;
import com.jumia.jumiapay.web.pojo.Bank;
import com.jumia.jumiapay.web.pojo.CardResolutionResponse;
import com.jumia.jumiapay.web.pojo.response.BankCardStatResponse;
import com.jumia.jumiapay.web.util.RestServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Slf4j
@Service
public class CardManagementServiceImpl implements CardManagementService {

    @Value("${card-resolution-service-url}")
    private String cardDetailResolutionService;

    private BankCardService bankCardService;
    private RestServiceClient restServiceClient;
    public CardManagementServiceImpl(BankCardService bankCardService, RestServiceClient restServiceClient) {
        this.bankCardService = bankCardService;
        this.restServiceClient = restServiceClient;
    }

    @Override
    public BankCardDTO resolveCardDetails(String cardNumber) {

        if(StringUtils.isEmpty(cardNumber)){
            throw new BadRequestException("Missing required detail: Card number.");
        }

        if(!StringUtils.isNumeric(cardNumber)){
            throw new BadRequestException("Card Number must be only digits");
        }

        if(cardNumber.length() < 6){
            throw new BadRequestException("Card Number must be minimum of 6 digits");
        }

        if(cardNumber.length() > 6){
            cardNumber = cardNumber.substring(0, 6);
        }

        Optional<BankCard> bankCardOptional = bankCardService.findBankCardByCardNumber(cardNumber);
        if(bankCardOptional.isPresent()){
            BankCard bankCard = bankCardOptional.get();
            bankCard.setTotalRequest(bankCard.getTotalRequest() + 1);
            bankCardService.saveRecord(bankCard);
            return from(bankCardOptional.get());
        }

        ResponseEntity<CardResolutionResponse> responseEntity = restServiceClient.getApiRequestResponse(cardDetailResolutionService+"/"+cardNumber, CardResolutionResponse.class);
        if(responseEntity == null || responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null){
          throw new NotFoundException("Unable to resolve card details at the moment");
        }
        CardResolutionResponse cardResolutionResponse = responseEntity.getBody();
        log.info("Response data: {}", cardResolutionResponse.toString());
        String type = cardResolutionResponse.getType();
        String scheme = cardResolutionResponse.getScheme();
        String bankName = "";
        if(cardResolutionResponse.getBank() != null){
            Bank bank = cardResolutionResponse.getBank();
            bankName = bank.getName();
        }
        BankCard bankCard = new BankCard();
        bankCard.setBank(bankName);
        bankCard.setCardNumber(cardNumber);
        bankCard.setScheme(scheme);
        bankCard.setType(type);
        bankCard.setTotalRequest(1);
        bankCard = bankCardService.saveRecord(bankCard);
        return from(bankCard);
    }

    @Override
    public BankCardStatResponse getBankCardStatistics(int start, int limit) {

        Page<BankCard> bankCardPage = bankCardService.getBankCards(start, limit);
        long size = bankCardPage.getTotalElements();
        HashMap<String, Long> statMap = new HashMap<>();
        bankCardPage.get().forEach(bankCard -> statMap.put(bankCard.getCardNumber(), bankCard.getTotalRequest()));
        String payload = statMap.toString();
        System.out.println(payload);
        BankCardStatResponse statResponse = new BankCardStatResponse();
        statResponse.setLimit(limit);
        statResponse.setPayload(statMap);
        statResponse.setSize(size);
        statResponse.setStart(start);
        statResponse.setSuccess(true);
        return statResponse;
    }


    private BankCardDTO from(BankCard bankCard){
        BankCardDTO cardDTO = new BankCardDTO();
        cardDTO.setBank(StringUtils.defaultString(bankCard.getBank()));
       // cardDTO.setCardNumber(bankCard.getCardNumber());
        cardDTO.setScheme(bankCard.getScheme());
        cardDTO.setType(bankCard.getType());
        return cardDTO;
    }
}
