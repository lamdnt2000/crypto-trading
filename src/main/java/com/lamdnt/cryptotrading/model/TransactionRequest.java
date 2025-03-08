package com.lamdnt.cryptotrading.model;

import com.lamdnt.cryptotrading.entity.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class TransactionRequest implements Serializable {
    private TransactionType type;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private Long tradingPairId;
}
