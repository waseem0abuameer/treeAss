package com.example.treeassignment.service.mapper;

import com.example.treeassignment.model.Account;
import com.example.treeassignment.model.Statement;
import com.example.treeassignment.model.dto.StatementDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * The type Statement mapper test.
 */
class StatementMapperTest {

    /**
     * Test convert to statement dto.
     */
    @Test
    void testConvertToStatementDTO() {
        Account account = new Account();
        account.setId(1L);
        Statement statement = new Statement(1L, account, LocalDate.of(2020, 8, 9), new BigDecimal("535.1978750270540"));

        StatementDTO statementDTO = StatementMapper.convertToStatementDTO(statement);

        assertEquals(1L, statementDTO.getAccountId());
        assertEquals("2020-08-09", statementDTO.getDateField());
        assertEquals("535.1978750270540", statementDTO.getAmount());
    }

    /**
     * Test convert to statement dt os.
     */
    @Test
    void testConvertToStatementDTOs() {
        Account account1 = new Account();
        account1.setId(1L);
        Statement statement1 = new Statement(1L, account1, LocalDate.of(2020, 8, 9), new BigDecimal("535.1978750270540"));

        Account account2 = new Account();
        account2.setId(2L);
        Statement statement2 = new Statement(2L, account2, LocalDate.of(2020, 9, 10), new BigDecimal("100.1234567890123"));

        List<Statement> statements = Arrays.asList(statement1, statement2);

        List<StatementDTO> statementDTOs = StatementMapper.convertToStatementDTOs(statements);

        assertEquals(2, statementDTOs.size());

        StatementDTO statementDTO1 = statementDTOs.get(0);
        assertEquals(1L, statementDTO1.getAccountId());
        assertEquals("2020-08-09", statementDTO1.getDateField());
        assertEquals("535.1978750270540", statementDTO1.getAmount());

        StatementDTO statementDTO2 = statementDTOs.get(1);
        assertEquals(2L, statementDTO2.getAccountId());
        assertEquals("2020-09-10", statementDTO2.getDateField());
        assertEquals("100.1234567890123", statementDTO2.getAmount());
    }

    /**
     * Test convert to statement.
     */
    @Test
    void testConvertToStatement() {
        StatementDTO statementDTO = new StatementDTO();
        statementDTO.setAccountId(1L);
        statementDTO.setDateField("2020-08-09");
        statementDTO.setAmount("535.1978750270540");

        Statement statement = StatementMapper.convertToStatement(statementDTO);

        assertEquals(1L, statement.getAccount().getId());
        assertEquals(LocalDate.of(2020, 8, 9), statement.getDatefield());
        assertEquals(new BigDecimal("535.1978750270540"), statement.getAmount());
    }

    /**
     * Test convert to statement null account id.
     */
    @Test
    void testConvertToStatement_NullAccountId() {
        StatementDTO statementDTO = new StatementDTO();
        statementDTO.setAccountId(null);  // setting accountId to null
        statementDTO.setDateField("2020-08-09");
        statementDTO.setAmount("535.1978750270540");

        NullPointerException thrown = assertThrows(NullPointerException.class, () -> {
            StatementMapper.convertToStatement(statementDTO);
        });

        assertEquals("The Statement Should not be null", thrown.getMessage());
    }
}