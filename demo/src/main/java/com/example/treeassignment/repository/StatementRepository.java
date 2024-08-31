package com.example.treeassignment.repository;

import com.example.treeassignment.model.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * The interface Statement repository.
 */
public interface StatementRepository extends JpaRepository<Statement, Long> {
    /**
     * Find statements by account and date range and amount list.
     *
     * @param accountId the account id
     * @param startDate the start date
     * @param endDate   the end date
     * @param min       the min
     * @param max       the max
     * @return the list
     */
    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId AND s.datefield BETWEEN :startDate AND :endDate AND s.amount BETWEEN :min AND :max")
    List<Statement> findStatementsByAccountAndDateRangeAndAmount(@Param("accountId") Long accountId,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate,
                                                        @Param("min") BigDecimal min,
                                                        @Param("max") BigDecimal max);

    /**
     * Find statements by account and date range list.
     *
     * @param accountId the account id
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId AND s.datefield BETWEEN :startDate AND :endDate")
    List<Statement> findStatementsByAccountAndDateRange(@Param("accountId") Long accountId,
                                                        @Param("startDate") LocalDate startDate,
                                                        @Param("endDate") LocalDate endDate);

    /**
     * Find statements by account and amount list.
     *
     * @param accountId the account id
     * @param min       the min
     * @param max       the max
     * @return the list
     */
    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId AND s.amount BETWEEN :min AND :max")
    List<Statement> findStatementsByAccountAndAmount(@Param("accountId") Long accountId,
                                                     @Param("min") BigDecimal min,
                                                     @Param("max") BigDecimal max);

    /**
     * Find all user list.
     *
     * @param accountId the account id
     * @param fromDate  the from date
     * @param toDate    the to date
     * @return the list
     */
    @Query("SELECT s FROM Statement s WHERE s.account.id = :accountId AND s.datefield BETWEEN :startDate AND :endDate")
    List<Statement> findAllUser(@Param("accountId") Long accountId,
                                @Param("startDate") LocalDate fromDate,
                                @Param("endDate") LocalDate toDate);
}
