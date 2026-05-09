package org.treasure.accountservice.mapper;

import org.mapstruct.Mapper;
import org.treasure.accountservice.domain.AccountDomain;
import org.treasure.accountservice.web.controller.dto.response.AccountResponse;

import java.util.List;

@Mapper
public interface AccountResponseMapper {

    AccountResponse map(AccountDomain domain);

    List<AccountResponse> mapAll(List<AccountDomain> domain);
}
