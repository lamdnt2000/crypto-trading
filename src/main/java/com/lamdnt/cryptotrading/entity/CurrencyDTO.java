package com.lamdnt.cryptotrading.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CurrencyDTO implements Serializable {
  private String name;
}
