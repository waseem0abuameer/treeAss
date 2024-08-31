package com.example.treeassignment.model.dto;

/**
 * The type Statement dto.
 */
public class StatementDTO {

    private Long accountId;
    private String dateField;
    private String amount;

    /**
     * Instantiates a new Statement dto.
     */
    public StatementDTO() {
    }

    /**
     * Instantiates a new Statement dto.
     *
     * @param accountId the account id
     * @param dateField the date field
     * @param amount    the amount
     */
    public StatementDTO( Long accountId, String dateField, String amount) {
        this.accountId = accountId;
        this.dateField = dateField;
        this.amount = amount;
    }


    /**
     * Gets account id.
     *
     * @return the account id
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     * Sets account id.
     *
     * @param accountId the account id
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * Gets date field.
     *
     * @return the date field
     */
    public String getDateField() {
        return dateField;
    }

    /**
     * Sets date field.
     *
     * @param dateField the date field
     */
    public void setDateField(String dateField) {
        this.dateField = dateField;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }
}