package com.example.learn.Model;

//this class will be used to store a row in the table

public class ProtoTransactionData
{


   // PRIMARY KEY for the table
   private int trans_id;

   private String trans_type;
   private String acct_type;

   private double open_bal;
   private double closing_bal;

   private double amount;

   public ProtoTransactionData()
   {
      // null
   }

   // constructor
   public ProtoTransactionData(int trans_id, String trans_type, String acct_type, double open_bal, double closing_bal, double amount)
   {
      // PRIMARY KEY
      this.trans_id = trans_id;

      this.trans_type = trans_type;
      this.acct_type = acct_type;

      this.open_bal = open_bal;
      this.closing_bal = closing_bal;

      this.amount = amount;
   }

   // constructor
   public ProtoTransactionData(String trans_type, String acct_type, double open_bal, double closing_bal, double amount)
   {
      this.trans_type = trans_type;
      this.acct_type = acct_type;

      this.open_bal = open_bal;
      this.closing_bal = closing_bal;

      this.amount = amount;
   }

   // getters
   public int getTransId()
   {return trans_id;}

   public String getTransType()
   {return trans_type;}

   public String getAcctType()
   {return acct_type;}

   public double getOpenBal()
   {return open_bal;}

   public double getClosingBal()
   {return closing_bal;}

   public double getAmount()
   {return amount;}


   // setters
   public void setTransId(int id)
   {trans_id = id;}

   public void setTransType(String trans_type)
   {this.trans_type = trans_type;}

   public void setAcctType(String acct_type)
   {this.acct_type = acct_type;}

   public void setOpenBalance(double open_bal)
   {this.open_bal = open_bal;}

   public void setClosingBalance(double closing_bal)
   {this.closing_bal = closing_bal;}

   public void setAmount(double amount)
   {this.amount = amount;}
} //end CLASS









