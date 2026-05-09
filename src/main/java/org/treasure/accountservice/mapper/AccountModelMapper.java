package org.treasure.accountservice.mapper;

import org.mapstruct.Mapper;
import org.treasure.accountservice.domain.AccountDomain;
import org.treasure.accountservice.model.AccountModel;

@Mapper
public interface AccountModelMapper {

    AccountModel map(AccountDomain model);
}
