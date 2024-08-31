package com.example.treeassignment.service.mapper;


import com.example.treeassignment.model.Account;
import com.example.treeassignment.model.Statement;
import com.example.treeassignment.model.dto.StatementDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Statement mapper.
 */
public class StatementMapper {
    private StatementMapper() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Convert to statement dto statement dto.
     *
     * @param statement the statement
     * @return the statement dto
     */
    public static StatementDTO convertToStatementDTO(Statement statement) {
        StatementDTO statementDTO = new StatementDTO();
        statementDTO.setAccountId(statement.getAccount().getId());
        statementDTO.setDateField(statement.getDatefield().toString());
        statementDTO.setAmount((statement.getAmount()).toString());
        return statementDTO;
    }

    /**
     * Convert to statement dt os list.
     *
     * @param statements the statements
     * @return the list
     */
    public static List<StatementDTO> convertToStatementDTOs(List<Statement> statements) {
        return statements.stream().map(StatementMapper::convertToStatementDTO).collect(Collectors.toList());
    }

    /**
     * Convert to statement statement.
     *
     * @param statementDTO the statement dto
     * @return the statement
     */
    public static Statement convertToStatement(StatementDTO statementDTO) {
        if(statementDTO.getAccountId() == null) {
            throw  new NullPointerException("The Statement Should not be null");
        }
        Statement statement = new Statement();
        statement.setDatefield(LocalDate.parse(statementDTO.getDateField()));
        BigDecimal amountDto = new BigDecimal(statementDTO.getAmount());
        statement.setAmount(amountDto);
        Account account = new Account();
        account.setId(statementDTO.getAccountId());
        statement.setAccount(account);
        return statement;
    }

}
