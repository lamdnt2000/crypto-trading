package com.lamdnt.cryptotrading.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CurrencyDTO implements Serializable {
    private String name;
}
