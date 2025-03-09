package com.lamdnt.cryptotrading.mapper;

import com.lamdnt.cryptotrading.entity.TradingPair;
import com.lamdnt.cryptotrading.model.TradingPairDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradingPairMapper {
  TradingPairMapper INSTANCE = Mappers.getMapper(TradingPairMapper.class);

  TradingPairDTO toDTO(TradingPair tradingPair);
}
