package com.jumia.jumiapay.web.pojo;

import lombok.Data;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Data
public class CardResolutionResponse {

     private Bank bank;

    private String scheme;

    private String type;

    private String brand;

    private boolean prepaid;

}
