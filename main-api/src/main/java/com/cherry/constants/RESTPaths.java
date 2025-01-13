package com.cherry.constants;

public class RESTPaths {
    public final static String BASE = "/api";

    // Accounts
    public final static String ACCOUNTS = BASE + "/cash";
    public final static String ACCOUNT_ID = ACCOUNTS + "/{id}";

    // Transactions
    public final static String TRANSACTIONS = BASE + "/transactions";
    public final static String TRANSACTION_ID = TRANSACTIONS + "/{id}";

    public final static String REFUNDS = BASE + "/refunds";
    public final static String REFUNDS_ID = REFUNDS + "/{id}";

    public final static String TRANSFERS = BASE + "/transfers";
    public final static String TRANSFERS_ID = TRANSFERS + "/{id}";
}