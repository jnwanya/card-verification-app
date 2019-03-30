package com.jumia.jumiapay.persistence.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jnwanya on
 * Sat, 30 Mar, 2019
 */

@Setter
@Getter
@Entity
@Table(name = "bank_card")
public class BankCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String scheme;

    @Column(nullable = false)
    private String type;

    private String bank;
}
