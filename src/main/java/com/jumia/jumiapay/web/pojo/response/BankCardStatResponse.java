package com.jumia.jumiapay.web.pojo.response;

import lombok.Data;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */
@Data
public class BankCardStatResponse {

    private boolean success;

    private long size;

    private int start;

    private int limit;

    private Object payload;
}
