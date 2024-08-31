package com.example.treeassignment.service;

import com.example.treeassignment.handler.AccountNotFoundException;
import com.example.treeassignment.model.Account;
import com.example.treeassignment.model.dto.AccountDTO;
import com.example.treeassignment.repository.AccountRepository;
import com.example.treeassignment.service.mapper.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.example.treeassignment.util.StringUtils.maskString;

/**
 * The type Account service.
 */
@Service
public class AccountService {


    private AccountRepository accountRepository;

    /**
     * Instantiates a new Account service.
     *
     * @param accountRepository the account repository
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Save account account dto.
     *
     * @param accountDTO the account dto
     * @return the account dto
     * @throws AccountNotFoundException the account not found exception
     */
    public AccountDTO  saveAccount(AccountDTO accountDTO) throws AccountNotFoundException {
        Account account = AccountMapper.convertToAccount(accountDTO);
            accountRepository.save(account);
        return AccountMapper.convertToAccountDTO(account);
    }

    /**
     * Save all accounts list.
     *
     * @param accounts the accounts
     * @return the list
     */
    @Transactional
    public List<Account> saveAllAccounts(List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }

    /**
     * Gets all accounts.
     *
     * @return the all accounts
     */
    public List<AccountDTO> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        accounts.stream()
                .forEach(account -> account.setAccountNumber(maskString(account.getAccountNumber())));

              return AccountMapper.convertToAccountDTOs(accounts);

    }

    /**
     * Gets account by id.
     *
     * @param id the id
     * @return the account by id
     * @throws AccountNotFoundException the account not found exception
     */
    public Account getAccountById(Long id) throws AccountNotFoundException {
        try {
            return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));

        }catch (Exception e) {
            throw new AccountNotFoundException("Account not found");
        }
    }

    /**
     * Delete account.
     *
     * @param id the id
     */
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}