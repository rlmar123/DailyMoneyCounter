package com.example.learn.Util;

public class UtilVar
{
   //Database related items
   public static final int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME = "transaction_db";
   public static final String TABLE_NAME = "transaction_data";

   //items table columns names

   //used for primary key
   public static final String TRANSACTION_ID = "transaction_id";

   public static final String TRANS_TYPE = "transaction_type";
   public static final String ACCT_TYPE = "account_type";
   public static final String OPEN_BALANCE = "open_balance";
   public static final String CLOSE_BAANCE = "close_balance";
   public static final String TRANS_AMOUNT = "transaction_amount";
}
