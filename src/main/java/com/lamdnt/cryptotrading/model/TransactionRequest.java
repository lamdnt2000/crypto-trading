package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
public class TransactionRequest implements Serializable {
  private TransactionType type;
  @NonNull private BigDecimal amount;
  @NonNull private Long tradingPairId;
}
