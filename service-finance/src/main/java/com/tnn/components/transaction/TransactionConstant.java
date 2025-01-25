package com.tnn.components.transaction;

public class TransactionConstant {
    // DB Table
    public static final String TABLE_NAME = "transaction";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_FROM_ACCOUNT = "fromAccountId";
    public static final String COLUMN_TO_ACCOUNT = "toAccountId";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    // JSON Keys
    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_TYPE = "type";
    public static final String JSON_KEY_FROM_ACCOUNT = "fromAccountId";
    public static final String JSON_KEY_TO_ACCOUNT = "toAccountId";
    public static final String JSON_KEY_AMOUNT = "amount";
    public static final String JSON_KEY_TIMESTAMP = "timestamp";

    // Constraints & Error Messages
    public static final String ERROR_TYPE_REQUIRED = "Transaction type is required.";
    public static final String ERROR_FROM_ACCOUNT_REQUIRED = "From account is required.";
    public static final String ERROR_TO_ACCOUNT_REQUIRED = "To account is required.";
    public static final String ERROR_AMOUNT_REQUIRED = "Amount is required.";
    public static final String ERROR_AMOUNT_POSITIVE = "Amount must be larger than 0.";
    public static final String ERROR_TIMESTAMP_REQUIRED = "Timestamp is required.";
}