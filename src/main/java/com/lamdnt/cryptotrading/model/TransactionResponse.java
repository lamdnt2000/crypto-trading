package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.TransactionType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionResponse implements Serializable {
  private Long id;
  private TransactionType type;
  private String symbol;
  private UUID txId;
  private BigDecimal price;
  private BigDecimal quantity;
  private BigDecimal quoteQuantity;
  private LocalDateTime createdAt;
}
