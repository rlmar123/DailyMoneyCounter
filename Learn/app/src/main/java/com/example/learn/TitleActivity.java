package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.learn.Data.OurDB;
import com.example.learn.Model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Random;

public class TitleActivity extends AppCompatActivity
{
   // open_pop_up.xml widgets
   private Button start_button = null;
   private Button submit_button = null;
   private FloatingActionButton tesr = null;

   private EditText first_name_field = null;
   private EditText last_name_field = null;

   private EditText savings_account_field = null;
   private EditText checking_account_field = null;

   // savings_pop_up.xml widgets
   private EditText savings_balance_text = null;

   // checking_pop_up.xml widgets
   private EditText checking_balance_text = null;

   private AlertDialog.Builder builder;
   private AlertDialog dialog;

   private double savings_balance;
   private double checking_balance;

   private Person user = null;
   private OurDB title_db = null;

   private int num = 0;

   private static final String MESSAGE_ID = "person_prefs";
   private SharedPreferences sharedPreferences = null;;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_title);

      title_db = new OurDB(this);

      //clears out shared prefs
      //sharedPreferences =  getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
     // sharedPreferences.edit().clear().commit();

      start_button = findViewById(R.id.start_button);


      start_button.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {

            myByPassMethod();
            createOpenPopupDialog();
         }
      });

   } // end onCreate


   private void createOpenPopupDialog()
   {
      // new person obj
      user = new Person();

      builder = new AlertDialog.Builder(this);
      View open_view = getLayoutInflater().inflate(R.layout.open_pop_up, null);

      first_name_field = open_view.findViewById(R.id.first_name);
      last_name_field = open_view.findViewById(R.id.last_name);

      savings_account_field = open_view.findViewById(R.id.savings_answer);
      checking_account_field = open_view.findViewById(R.id.checking_answer);

      submit_button = open_view.findViewById(R.id.personal_info_button);

      builder.setView(open_view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

      submit_button.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            // all fields filled
            if ( (!first_name_field.getText().toString().isEmpty()) && (!last_name_field.getText().toString().isEmpty()) && (!savings_account_field.getText().toString().isEmpty()) && (!checking_account_field.getText().toString().isEmpty()) )
            {
               dialog.dismiss();
               getData(v);
            }

            // a field is missing
            else
               Toast.makeText(TitleActivity.this, "Missing a field", Toast.LENGTH_LONG).show();

         } // end onClick

      });

   } // end createOpenPopupDialog


/*
   private void createCheckingDialog()
   {
      builder = new AlertDialog.Builder(this);
      View checking_view = getLayoutInflater().inflate(R.layout.checking_pop_up, null);

      builder.setView(checking_view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

   } // end createCheckingDialog

   private void createSavingsDialog()
   {
      builder = new AlertDialog.Builder(this);
      View savings_view = getLayoutInflater().inflate(R.layout.savings_pop_up, null);

      builder.setView(savings_view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

   } // end createSavingsDialog  */


   private void getData(View v)
   {
      createPerson();

      // store user data in shared prefs
      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      SharedPreferences.Editor editor = sharedPreferences.edit();

      editor.putString("f_name", user.getFirstName());
      editor.putString("l_name", user.getLastName());

      editor.putInt("acct_num", user.getAccountNumber());

      editor.putBoolean("has_savings", user.hasSavings());
      editor.putBoolean("has_checking", user.hasChecking());

      editor.putFloat("chrck_bal", (float) user.getCheckingBalance());
      editor.putFloat("save_bal", (float)user.getSavingsBalance());

      editor.apply();

      // store user data in shared prefs
      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      Intent nextIntent = new Intent(TitleActivity.this, SavingsActivity.class);
      nextIntent.putExtra("the_user", user);

      startActivity(nextIntent);
      finish();
   } // end getData


   private void createPerson()
   {
      //convert first and last name to string to insert into user object
      String first = first_name_field.getText().toString().trim();
      String last = last_name_field.getText().toString().trim();

      String savings = savings_account_field.getText().toString().trim();
      String checking = checking_account_field.getText().toString().trim();

      savings_balance = Double.parseDouble(savings);
      checking_balance = Double.parseDouble(checking);
      user.setFirstName(first);
      user.setLastName(last);

      double min = 5.00;

      //test checking_bslance
      if(checking_balance > min)
      {
         int acct_num = 10000 + new Random().nextInt(90000);

         user.setChecking(true);
         user.setAccountNumber(acct_num);
         user.setCheckingBalance(checking_balance);
      }

      else if(checking_balance <= min)
      {
         user.setChecking(false);
         user.setCheckingBalance(0.00);
      }


      //test savings_balance
      if(savings_balance > min)
      {
         user.setSavings(true);
         user.setSavingsBalance(savings_balance);
      }

      else if(savings_balance <= min)
      {
         user.setSavings(false);
         user.setSavingsBalance(0.00);
      }

   } // end createAperson


   private void myByPassMethod()
   {
      // WE need to pass person object
      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      if(sharedPreferences.getString("f_name", null) != null)
      {
         // new person obj
         user = new Person();

         user.setFirstName(sharedPreferences.getString("f_name", null));
         user.setLastName(sharedPreferences.getString("l_name", null));
         user.setSavings(sharedPreferences.getBoolean("has_savings", false));
         user.setChecking(sharedPreferences.getBoolean("has_checking", false));
         user.setAccountNumber(sharedPreferences.getInt("acct_num", -1));
         user.setCheckingBalance(sharedPreferences.getFloat("chrck_bal", -1));
         user.setSavingsBalance(sharedPreferences.getFloat("save_bal", -1));

         Intent myByPassIntent = new Intent(TitleActivity.this, SavingsActivity.class);
         myByPassIntent.putExtra("the_user", user);

         startActivity(myByPassIntent);
         Log.d("ProtoTransactionData_1", "my_by_pass " + sharedPreferences.getFloat("chrck_bal", -1));
         finish();
      }

   }

} // end TitleActivity
