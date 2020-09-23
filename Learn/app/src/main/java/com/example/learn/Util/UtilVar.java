package com.example.learn.Util;

public class UtilVar
{
   //Database related items, used to be 1
   public static final int DATABASE_VERSION = 2;
   public static final String DATABASE_NAME = "new_transaction_db";
   public static final String TABLE_NAME = "transaction_data";

   //items table columns names
   //used for primary key
   public static final String TRANSACTION_ID = "transaction_id";

   public static final String TRANS_TYPE = "transaction_type";
   public static final String ACCT_TYPE = "account_type";
   public static final String OPEN_BALANCE = "open_balance";
   public static final String CLOSE_BAANCE = "close_balance";
   public static final String TRANS_AMOUNT = "transaction_amount";
   public static final String TRANS_DATE = "transaction_date";
   public static final String TRANS_DESCRIPTION = "transaction_description";
}
