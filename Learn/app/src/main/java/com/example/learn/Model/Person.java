package com.example.learn.Model;

import java.io.Serializable;
import java.util.Random;

public class Person implements Serializable
{

    Random acct_num_generator = null;

    private String first_name = null;
    private String last_name = null;

    private double checking_balance = 0;
    private double savings_balance = 0;

    private int account_number;

    boolean savings = false;
    boolean checking = false;


    public Person()
    {/*null*/}

    public Person(String fName, String lName, double checking_bal, double savings_bal, int account_num, boolean save, boolean check)
    {
        first_name = fName;
        last_name = lName;

        checking_balance = checking_bal;
        savings_balance = savings_bal;

        account_number = account_num;

        savings = save;
        checking = check;

    //    person_count++;
    }

    // getter methods
    public String getFirstName()
       {return first_name;}

    public String getLastName()
       {return last_name;}

    public double getCheckingBalance()
       {return checking_balance;}

    public double getSavingsBalance()
       {return savings_balance;}

    public int getAccountNumber()
       {return account_number;}

    public boolean hasChecking()
       {return checking;}

    public boolean hasSavings()
       {return savings;}

   // setter methods
   public void setFirstName(String first_name)
      {this.first_name = first_name;}

   public void setLastName(String last_name)
      {this.last_name = last_name;}

   public void setCheckingBalance(double checking_balance)
      {this.checking_balance = checking_balance;}

   public void setSavingsBalance(double savings_balance)
      {this.savings_balance = savings_balance;}

   public void setAccountNumber(int account_number)
      {this.account_number = account_number;}

   public void setChecking(boolean checking)
      {this.checking = checking;}

   public void setSavings(boolean savings)
      {this.savings = savings;}
} // end class
