package com.jumia.jumiapay.web.pojo.response;

import com.jumia.jumiapay.infrastructure.dto.BankCardDTO;
import lombok.Data;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Data
public class BankCardVerificationResponse {

     private boolean success;

     private BankCardDTO payload;
}
