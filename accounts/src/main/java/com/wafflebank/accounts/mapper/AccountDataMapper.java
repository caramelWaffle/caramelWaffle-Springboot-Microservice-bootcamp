package com.wafflebank.accounts.mapper;

import com.wafflebank.accounts.entity.AccountEntity;
import com.wafflebank.accounts.model.account.AccountData;
import com.wafflebank.accounts.model.account.AccountType;

public class AccountDataMapper {
    public static AccountData toData(AccountEntity entity) {
        if (entity == null) {
            return null;
        }
        AccountData data = new AccountData();
        data.setAccountNumber(entity.getAccountNumber());
        data.setAccountType(AccountType.valueOf(entity.getAccountType()));
        data.setMobileNumber(entity.getMobileNumber());
        data.setBranch(entity.getBranch());
        return data;
    }

    public static AccountEntity toEntity(AccountData data) {
        if (data == null) {
            return null;
        }
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(data.getAccountNumber());
        entity.setAccountType(data.getAccountType().name());
        entity.setBranch(data.getBranch());
        entity.setMobileNumber(data.getMobileNumber());
        return entity;
    }
}
