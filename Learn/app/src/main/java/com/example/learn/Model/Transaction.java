package com.example.learn.Model;

//This class does the actual computations and creates a TransactionData Object.
public class Transaction
{
    //PK in our DB
    private static int transaction_count = 0;

    private double open_balance = 0;
    private double close_balance = 0;

    private String transaction_type = null;

    private final String DEPOSIT = "Deppsit";
    private final String WITHDRAW = "Withdraw";
    private final String TRANSFER = "Transfer";


    public Transaction()
    {
       transaction_count++;
    }

    public void deposit()
    {
        transaction_type = DEPOSIT;
        // here
    }

    public void withdraw()
    {
        transaction_type = WITHDRAW;
        //here

    }


    public void transfer(Person current_user, boolean from_checking, boolean from_savings, int amount)
    {
       transaction_type = TRANSFER;

       double checking_balance = current_user.getCheckingBalance();
       double savings_balance = current_user.getSavingsBalance();

       //transfer from checking -> savings
       if(from_checking)
       {
           // verify the account has enough for transfer
          if(checking_balance >= amount)
          {
             checking_balance -= amount;
             savings_balance += amount;
          }

          else
          {
             //write an error statement
          }
       }

       //transfer from savings -> checking
       else if(from_savings)
       {
          // verify the account has enough for transfer
          if(savings_balance >= amount)
          {
             savings_balance -= amount;
             checking_balance += amount;
          }

          else
          {
              //write an error statement
          }
       }

       current_user.setCheckingBalance(checking_balance);
       current_user.setSavingsBalance(savings_balance);

    } // end transfer

}
