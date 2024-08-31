package com.example.treeassignment.model.dto;

import jakarta.validation.constraints.NotEmpty;

/**
 * The type Account dto.
 */
public class AccountDTO {
    private Long id;
    @NotEmpty(message = "Account Type cannot be null or Empty")
    private String accountType;
    @NotEmpty(message = "Account Number cannot be null or Empty")
    private String accountNumber;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets account type.
     *
     * @param accountType the account type
     */
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets account number.
     *
     * @param accountNumber the account number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}