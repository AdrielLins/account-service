package org.treasure.accountservice.mapper;

import org.mapstruct.Mapper;
import org.treasure.accountservice.domain.AccountDomain;
import org.treasure.accountservice.model.AccountModel;
import org.treasure.accountservice.web.controller.dto.request.AccountRequest;

@Mapper
public interface AccountDomainMapper {

    AccountDomain map(AccountModel model);
    AccountDomain map(AccountRequest request);
}
