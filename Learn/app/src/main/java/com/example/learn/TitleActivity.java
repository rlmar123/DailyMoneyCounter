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



public class TitleActivity extends AppCompatActivity {

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
   private SharedPreferences sharedPreferences= null;;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_title);


      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      title_db = new OurDB(this);


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
               getData(v);
               dialog.dismiss();
            }

            // a field is missing
            else
               Toast.makeText(TitleActivity.this, "Missing a field", Toast.LENGTH_LONG).show();

         //  Toast.makeText(TitleActivity.this, "first name " + user.getFirstName(), Toast.LENGTH_LONG).show();


         } // end onClick

      });





   } // end createOpenPopupDialog

   /*
   // this method chooses which popup to create based on the savings_response and checking_response
   private void dialogCreator()
   {
      // test savings_response
      if ((savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y'))
      {
         user.setSavings(true);
         createSavingsDialog();

      }

      else if ((savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n'))

      {
         user.setSavings(false);
         Toast.makeText(TitleActivity.this, "No Savings Account created...", Toast.LENGTH_LONG).show();
      }


      // test checking_response
      if ((checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y'))
      {
         user.setChecking(true);
         createCheckingDialog();
      }

      else if ((checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n'))
      {
         user.setChecking(false);
         Toast.makeText(TitleActivity.this, "No Checking Account created...", Toast.LENGTH_LONG).show();
      }

   } // end dialogCreator */


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

   } // end createSavingsDialog


   // this method tests to make sure that the user entered Yes or No
   private boolean accountFieldValidator(String savings_response, String checking_response)
   {
      boolean isGood = false;

      if(( (savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y') ) && ( (checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y') ))
      {
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is Yes checking is yes", Toast.LENGTH_LONG).show();
      }

      else if ( ( (savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n') ) && ( (checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y') ) )
      {
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is no checking is yes", Toast.LENGTH_LONG).show();
      }

      else if ( ( (savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y') ) && ( (checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n') ) )
      {
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is Yes checking is no", Toast.LENGTH_LONG).show();
      }

      else if ( ( (savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n') ) && ( (checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n') ) )
      {
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is no checking is no", Toast.LENGTH_LONG).show();
      }

      else
      {
         Toast.makeText(TitleActivity.this, "Please enter Y or N for checking and savings....", Toast.LENGTH_LONG).show();
      }

      return isGood;
   } //end accountFieldValidator


   private void getData(View v)
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

      if(checking_balance > min)
      {
           user.setChecking(true);
           user.setCheckingBalance(checking_balance);
      }

      else if(checking_balance == min)
      {
         user.setChecking(false);
         user.setCheckingBalance(0.00);
      }



      if(savings_balance > min)
      {
         user.setSavings(true);
         user.setSavingsBalance(savings_balance);

      }

      else if(savings_balance == min)
      {
         user.setSavings(false);
         user.setSavingsBalance(0.00);
      }

      Log.d("ProtoTransactionData_1", "user has checking :  " + user.hasChecking());
      Log.d("ProtoTransactionData_1", "user checking balance :  " + user.getCheckingBalance());
      Log.d("ProtoTransactionData_1", "user has savings :  "+ user.hasSavings());
      Log.d("ProtoTransactionData_1", "user checking balance :   " + user.getSavingsBalance());

      Intent nextIntent = new Intent(TitleActivity.this, SavingsActivity.class);
      nextIntent.putExtra("the_user", user);

      startActivity(nextIntent);
      finish();
   } // end getData


   private void myByPassMethod()
   {
      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      if( title_db.getCount() > 0)
      {
         Intent myByPassIntent = new Intent(TitleActivity.this, SavingsActivity.class);
         startActivity(myByPassIntent);
         Log.d("ProtoTransactionData_1", "my_title" + title_db.getCount());
         finish();
      }

   }


} // end TitleActivity
