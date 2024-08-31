package com.example.treeassignment.service.mapper;



import com.example.treeassignment.model.Account;
import com.example.treeassignment.model.dto.AccountDTO;
import com.example.treeassignment.model.exstarnalenum.AccountType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Account mapper.
 */
public class AccountMapper {
    private AccountMapper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Convert to account dto account dto.
     *
     * @param account the account
     * @return the account dto
     */
    public static AccountDTO convertToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountType(String.valueOf(account.getAccountType()).toUpperCase());
        accountDTO.setAccountNumber(account.getAccountNumber());
        return accountDTO;
    }


    /**
     * Convert to account dt os list.
     *
     * @param accounts the accounts
     * @return the list
     */
    public static List<AccountDTO> convertToAccountDTOs(List<Account> accounts) {
        return accounts.stream().map(AccountMapper::convertToAccountDTO).collect(Collectors.toList());
    }


    /**
     * Convert to account account.
     *
     * @param accountDTO the account dto
     * @return the account
     */
    public static Account convertToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setAccountType(AccountType.valueOf(accountDTO.getAccountType().toUpperCase()));
        account.setAccountNumber(accountDTO.getAccountNumber());
        return account;
    }

}