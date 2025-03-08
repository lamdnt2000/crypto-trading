package com.lamdnt.cryptotrading.mapper;

import com.lamdnt.cryptotrading.entity.Account;
import com.lamdnt.cryptotrading.model.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO toDTO(Account account);
}
