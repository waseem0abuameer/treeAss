package com.example.treeassignment.service;

import com.example.treeassignment.handler.DateNotinvaledFormatException;
import com.example.treeassignment.handler.InvalidDateRangeException;
import com.example.treeassignment.model.Statement;
import com.example.treeassignment.repository.StatementRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Statement service.
 */
@Service
public class StatementService {
    private StatementRepository statementRepository;

    /**
     * Instantiates a new Statement service.
     *
     * @param statementRepository the statement repository
     */
    public StatementService(StatementRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    /**
     * Save statement statement.
     *
     * @param statement the statement
     * @return the statement
     */
    public Statement saveStatement(Statement statement) {
        return statementRepository.save(statement);
    }

    /**
     * Find all statements list.
     *
     * @param accountId the account id
     * @return the list
     */
    public List<Statement> findAllStatements(Long accountId) {
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = toDate.withMonth(toDate.getMonthValue() - 3);

        return statementRepository.findAllUser(accountId, fromDate, toDate);

    }

    /**
     * Find all by date field between list.
     *
     * @param accountId the account id
     * @param fromDate  the from date
     * @param toDate    the to date
     * @param min       the min
     * @param max       the max
     * @return the list
     * @throws DateNotinvaledFormatException the date notinvaled format exception
     */
    public List<Statement> findAllByDateFieldBetween(Long accountId, String fromDate, String toDate, String min, String max) throws DateNotinvaledFormatException {
        LocalDate parsedFromDate = parseDate(fromDate);
        LocalDate parsedToDate = parseDate(toDate);
        BigDecimal newMin = parseAmount(min);
        BigDecimal newMax = parseAmount(max);

        validateAmounts(newMin, newMax);
        validateDates(fromDate, toDate);

        if (isDateRangeEmpty(fromDate)) {
            return handleEmptyDateRange(accountId, newMin, newMax);
        } else if (isAmountEmpty(min)) {
            return statementRepository.findStatementsByAccountAndDateRange(accountId, parsedFromDate, parsedToDate);
        } else {
            return statementRepository.findStatementsByAccountAndDateRangeAndAmount(accountId, parsedFromDate, parsedToDate, newMin, newMax);
        }
    }

    private LocalDate parseDate(String date) throws DateNotinvaledFormatException {
        try {
            return (date != null) ? LocalDate.parse(date) : null;
        } catch (Exception e) {
            throw new DateNotinvaledFormatException("The date format is invalid");
        }
    }

    private BigDecimal parseAmount(String amount) throws DateNotinvaledFormatException {
        try {
            return (amount != null && !amount.isEmpty()) ? new BigDecimal(amount) : null;
        } catch (Exception e) {
            throw new DateNotinvaledFormatException("The amount format is invalid");
        }
    }

    private void validateAmounts(BigDecimal min, BigDecimal max) throws InvalidDateRangeException {
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new InvalidDateRangeException("The 'min amount' should be less than or equal to the 'max amount'.");
        }
    }

    private void validateDates(String fromDate, String toDate) throws InvalidDateRangeException {
        if (fromDate != null && toDate != null && fromDate.compareTo(toDate) > 0) {
            throw new InvalidDateRangeException("The 'fromDate' must be earlier than or equal to the 'toDate'.");
        }
    }

    private boolean isDateRangeEmpty(String fromDate) {
        return fromDate == null || fromDate.isEmpty();
    }

    private boolean isAmountEmpty(String amount) {
        return amount == null || amount.isEmpty();
    }

    private List<Statement> handleEmptyDateRange(Long accountId, BigDecimal min, BigDecimal max) {
        if (min == null) {
            return findAllStatements(accountId);
        } else {
            return statementRepository.findStatementsByAccountAndAmount(accountId, min, max);
        }
    }
}
