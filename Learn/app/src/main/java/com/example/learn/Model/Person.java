package com.example.learn.Model;

import java.util.Random;

public class Person
{
 //   private static int person_count = 0;
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
    public String getFirst_name()
       {return first_name;}

    public String getLast_name()
       {return last_name;}

    public double getChecking_balance()
       {return checking_balance;}

    public double getSavings_balance()
       {return savings_balance;}

    public int getAccount_number()
       {return account_number;}

    public boolean isChecking()
       {return checking;}

    public boolean isSavings()
       {return savings;}

   // setter methods
   public void setFirst_name(String first_name)
      {this.first_name = first_name;}

   public void setLast_name(String last_name)
      {this.last_name = last_name;}

   public void setChecking_balance(double checking_balance)
      {this.checking_balance = checking_balance;}

   public void setSavings_balance(double savings_balance)
      {this.savings_balance = savings_balance;}

   public void setAccount_number(int account_number)
      {this.account_number = account_number;}

   public void setChecking(boolean checking)
      {this.checking = checking;}

   public void setSavings(boolean savings)
      {this.savings = savings;}
} // end class
