package com.tnn.component.user;

public class UserConstant {
    // DB Table
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRST_NAME = "firstName";
    public static final String COLUMN_LAST_NAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_FAMILY = "familyId";
    public static final String COLUMN_PASSWORD = "password";

    // JSON Keys
    public static final String JSON_KEY_ID = "id";
    public static final String JSON_KEY_FIRST_NAME = "firstName";
    public static final String JSON_KEY_LAST_NAME = "lastName";
    public static final String JSON_KEY_EMAIL = "email";
    public static final String JSON_KEY_ROLE = "role";
    public static final String JSON_KEY_FAMILY = "familyId";
    public static final String JSON_KEY_PASSWORD = "password";

    // Constraints & Error Messages
    public static final String ERROR_FIRST_NAME_REQUIRED = "First name is required";
    public static final String ERROR_LAST_NAME_REQUIRED = "Last name is required";
    public static final int VALID_NAME_LENGTH_MIN = 1;
    public static final int VALID_NAME_LENGTH_MAX = 100;
    public static final String ERROR_FIRST_NAME_LENGTH_VALID = "First name must be between 1 and 100 characters.";
    public static final String ERROR_LAST_NAME_LENGTH_VALID = "Last name must be between 1 and 100 characters.";
    public static final String VALID_NAME_PATTERN = "^[a-zA-Z-]+$";
    public static final String ERROR_FIRST_NAME_PATTERN_VALID = "First name must only contain letters";
    public static final String ERROR_LAST_NAME_PATTERN_VALID = "Last name must only contain letters";
    public static final String ERROR_REPLACE_ID = "Path id %d doesn't match request body id %d";
    public static final String ERROR_EMAIL_REQUIRED = "Email is required";
    public static final String ERROR_EMAIL_PATTERN_VALID = "Email format is incorrect";
    public static final String ERROR_PASSWORD_REQUIRED = "Password is required";
    public static final String VALID_PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String ERROR_PASSWORD_PATTERN_VALID =
            "Password must contains lower and upper case characters, numbers, special characters, and of at least 8 characters long";
}