package com.cherry.components.account;

public class AccountConstant {
    // DB Table
    public static final String TABLE_NAME = "account";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_NAME = "name";

    // JSON Keys
    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_TYPE = "type";
    public static final String JSON_KEY_NAME = "name";

    // Constraints & Error Messages
    public static final String ERROR_TYPE_REQUIRED = "Account type is required.";
    public static final String ERROR_NAME_REQUIRED = "Account name is required.";
    public static final int VALID_NAME_LENGTH_MIN = 1;
    public static final int VALID_NAME_LENGTH_MAX = 100;
    public static final String ERROR_NAME_LENGTH_VALID = "Account name must be between 1 and 100 characters.";
    public static final String VALID_NAME_PATTERN = "^[a-zA-Z0-9 ]+$";
    public static final String ERROR_NAME_PATTERN_VALID = "Account name must only contain letters and numbers.";
    public static final String ERROR_REPLACE_ID = "Path id %d doesn't match request body id %d";
}