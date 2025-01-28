package com.tnn.component.family;

public class FamilyConstant {
    // DB Table
    public static final String TABLE_NAME = "family";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";

    // JSON Keys
    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_NAME = "name";

    // Constraints & Error Messages
    public static final String ERROR_NAME_REQUIRED = "Family name is required.";
    public static final int VALID_NAME_LENGTH_MIN = 1;
    public static final int VALID_NAME_LENGTH_MAX = 100;
    public static final String ERROR_NAME_LENGTH_VALID = "Family name must be between 1 and 100 characters.";
    public static final String VALID_NAME_PATTERN = "^[a-zA-Z0-9 ]+$";
    public static final String ERROR_NAME_PATTERN_VALID = "Family name must only contain letters and numbers.";
    public static final String ERROR_REPLACE_ID = "Path id %d doesn't match request body id %d";
}