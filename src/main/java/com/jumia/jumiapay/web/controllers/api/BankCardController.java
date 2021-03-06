package com.jumia.jumiapay.web.controllers.api;

import com.jumia.jumiapay.infrastructure.dto.BankCardDTO;
import com.jumia.jumiapay.infrastructure.usecase.CardManagementService;
import com.jumia.jumiapay.web.pojo.response.BankCardStatResponse;
import com.jumia.jumiapay.web.pojo.response.BankCardVerificationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@RestController
public class BankCardController {

    private CardManagementService cardManagementService;
    public BankCardController(CardManagementService cardManagementService) {
        this.cardManagementService = cardManagementService;
    }

    @GetMapping(value = "/card-scheme/verify/{cardNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankCardVerificationResponse> getBankCardDetails(@PathVariable("cardNumber") String cardNumber){

        BankCardDTO bankCardDTO = cardManagementService.resolveCardDetails(cardNumber);
        BankCardVerificationResponse response = new BankCardVerificationResponse();
        response.setPayload(bankCardDTO);
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/card-scheme/stats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankCardStatResponse> getBankCardStatistics(@RequestParam(name = "start") int start, @RequestParam(name = "limit") int limit){

         BankCardStatResponse bankCardStatResponse = cardManagementService.getBankCardStatistics(start, limit);
        return new ResponseEntity<>(bankCardStatResponse, HttpStatus.OK);
    }
}
