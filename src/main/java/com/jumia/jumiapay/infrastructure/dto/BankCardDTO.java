package com.jumia.jumiapay.infrastructure.dto;

import lombok.Data;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Data
public class BankCardDTO {

    private String bank;

    private String type;

    private String scheme;
}
