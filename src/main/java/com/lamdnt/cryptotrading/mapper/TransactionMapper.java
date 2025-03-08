package com.lamdnt.cryptotrading.mapper;

import com.lamdnt.cryptotrading.entity.Transaction;
import com.lamdnt.cryptotrading.model.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "symbol", source = "tradingPair.symbol")
    TransactionResponse toTransactionResponse(Transaction transaction);
}
