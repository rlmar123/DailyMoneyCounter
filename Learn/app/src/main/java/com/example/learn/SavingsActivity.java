package com.example.learn;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.NumberFormat;
import android.os.Bundle;

import com.example.learn.Data.OurDB;
import com.example.learn.Model.Person;
import com.example.learn.Model.ProtoTransactionData;
import com.example.learn.UI.TestRecycleView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SavingsActivity extends AppCompatActivity
{
   private AlertDialog.Builder builder;
   private AlertDialog dialog;

   //activity_savings.xml widget variables
   private RecyclerView recycler_view = null;
   private TestRecycleView test_adapter= null;

   private List<ProtoTransactionData> our_item_list = null;
   private OurDB the_db = null;

   // add_pop_up.xml widgets
   private TextView balance_text_savings = null;
   private EditText deposit_text_savings = null;
   private TextView sum_text_savings = null;

   // subtract_pop_up.xml widgets
   private TextView withdrawBal_text_savings = null;
   private EditText withdraw_text_savings = null;
   private TextView diff_text_savings = null;

   private Button confirm_button_savings = null;
   private Button cancel_button_savings = null;

   private Person the_user;

   private static final String MESSAGE_ID = "person_prefs";
   private SharedPreferences sharedPreferences = null;;

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_savings);
    //  Toolbar toolbar = findViewById(R.id.toolbar);
    //  setSupportActionBar(toolbar);

      Intent i = getIntent();
      the_user = (Person) i.getSerializableExtra("the_user");

      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

      Log.d("ProtoTransactionData_1", "F_name " + sharedPreferences.getString("f_name", null));
      Log.d("ProtoTransactionData_1", "l_name " + sharedPreferences.getString("l_name", null));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getInt("acct_num", 1));
      Log.d("ProtoTransactionData_1", "From title activity " + the_user.getCheckingBalance());
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getFloat("save_bal", -1));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getBoolean("has_savings", false));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getBoolean("has_checking", false));

      the_db = new OurDB(this);
     // the_db.clearDatabase();

      our_item_list = new ArrayList<ProtoTransactionData>();
      our_item_list = the_db.getAllTransactions();

      //connect to activity_main.xml widget
      recycler_view = findViewById(R.id.a_recycler);
      recycler_view.setHasFixedSize(true);
      recycler_view.setLayoutManager(new LinearLayoutManager(this));

      //setup recycler_adapter
      test_adapter = new TestRecycleView(this, our_item_list);
      recycler_view.setAdapter(test_adapter);

      //keeps data up to date
      test_adapter.notifyDataSetChanged();

      // FABS
      FloatingActionButton leftFab = findViewById(R.id.left_fab);
      leftFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            createSubtractPopupDialog();
         }
      });

      FloatingActionButton midFab = findViewById(R.id.mid_fab);

      //this will clear the db if user hits the mid_fab
      midFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {
            erase();
            Toast.makeText(SavingsActivity.this, "Mid works", Toast.LENGTH_LONG).show();
         }
      });

      FloatingActionButton rightFab = findViewById(R.id.right_fab);
      rightFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            createAddPopupDialog();

         }
      });

   } // end onCreate

   private void createAddPopupDialog()
   {
      builder = new AlertDialog.Builder(this);
      View view = getLayoutInflater().inflate(R.layout.add_pop_up, null);

      // to format into dollar format
      NumberFormat formatter = NumberFormat.getCurrencyInstance();

      // make connection to add_pop_up.xml widgets
      balance_text_savings = view.findViewById(R.id.balance_text);
      deposit_text_savings = view.findViewById(R.id.deposit_text);
      sum_text_savings = view.findViewById(R.id.sum_text);

      confirm_button_savings = view.findViewById(R.id.add_confirm_button);
      cancel_button_savings = view.findViewById(R.id.add_cancel_button);

      // we will use person savings bal here!!!!
      balance_text_savings.setText("Balance : " + "" + formatter.format(the_user.getCheckingBalance()));

      builder.setView(view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

      confirm_button_savings.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
          //  Toast.makeText(SavingsActivity.this, "CLICK CLICK CLICK", Toast.LENGTH_LONG).show();
            if(!(deposit_text_savings.getText().toString().isEmpty()))
               deposit();

            else
               Toast.makeText(SavingsActivity.this, "Deposit is blank...", Toast.LENGTH_LONG).show();
         }
      });

      cancel_button_savings.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dialog.dismiss();
         }
      });
   }

   private void createSubtractPopupDialog()
   {
      builder = new AlertDialog.Builder(this);
      View view = getLayoutInflater().inflate(R.layout.subtract_pop_up, null);

      // to format into dollar format
      NumberFormat formatter = NumberFormat.getCurrencyInstance();

      withdrawBal_text_savings = view.findViewById(R.id.withdraw_balance_text);
      withdraw_text_savings = view.findViewById(R.id.withdraw_text);
      diff_text_savings = view.findViewById(R.id.difference_text);

      confirm_button_savings = view.findViewById(R.id.add_confirm_button);
      cancel_button_savings = view.findViewById(R.id.add_cancel_button);

      withdrawBal_text_savings.setText("Balance : " + "" + formatter.format(the_user.getCheckingBalance()));

      builder.setView(view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

      confirm_button_savings.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            if(!(withdraw_text_savings.getText().toString().isEmpty()))
            {  String string_amount = withdraw_text_savings.getText().toString();
               double withdrawal = Double.parseDouble(string_amount);

               double checking = Math.round(the_user.getCheckingBalance() * 100.0) / 100.0;
               if(checking >= withdrawal)
               {
                  Toast.makeText(SavingsActivity.this, "Withdraw is good...", Toast.LENGTH_LONG).show();
                  withdraw();
               }

               else
                  Toast.makeText(SavingsActivity.this, "Insufficient Funds...", Toast.LENGTH_LONG).show();
            }

            else
               Toast.makeText(SavingsActivity.this, "Withdraw is blank...", Toast.LENGTH_LONG).show();
         }
      });

      cancel_button_savings.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dialog.dismiss();
         }
      });

   }

   private void deposit()
   {
      NumberFormat formatter = NumberFormat.getCurrencyInstance();

      final String DEPOSIT = "Deposit";

      String the_amount = deposit_text_savings.getText().toString().trim();

      final double open = the_user.getCheckingBalance();
      final double amount = Double.parseDouble(the_amount);
      final double closing = open + amount;

      sum_text_savings.setText(formatter.format(closing));

      // this restarts the activity
      new Handler().postDelayed(new Runnable()
      {

         @Override
         public void run()
         {
            Person tempPerson = new Person();

            sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

            ProtoTransactionData deposit_transaction = new ProtoTransactionData();

            tempPerson.setFirstName(sharedPreferences.getString("f_name", null));
            tempPerson.setLastName(sharedPreferences.getString("l_name", null));
            tempPerson.setSavings(sharedPreferences.getBoolean("has_savings", false));
            tempPerson.setChecking(sharedPreferences.getBoolean("has_checking", false));
            tempPerson.setAccountNumber(sharedPreferences.getInt("acct_num", -1));
            tempPerson.setCheckingBalance(closing);
            tempPerson.setSavingsBalance(sharedPreferences.getFloat("save_bal", -1));  //

            Intent myIntent = new Intent(SavingsActivity.this, SavingsActivity.class);
            myIntent.putExtra("the_user", tempPerson);
            dialog.dismiss();

            // setup the transaction for DB storage
            deposit_transaction.setTransType(DEPOSIT);
            deposit_transaction.setAcctType("Checking");
            deposit_transaction.setAmount(amount);
            deposit_transaction.setOpenBalance(open);
            deposit_transaction.setClosingBalance(closing);

            the_db.addTransaction(deposit_transaction);

            // to ensure balance is current when user turns off the app
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("chrck_bal", (float) tempPerson.getCheckingBalance());
            editor.apply();

            startActivity(myIntent);

            //kills previous activity
            finish();

         }
      }, 1500);

   } // end deposit

   private void withdraw()
   {
      NumberFormat formatter = NumberFormat.getCurrencyInstance();

      final String WITHDRAW = "Withdraw";
      String the_amount = withdraw_text_savings.getText().toString().trim();

      final double open = the_user.getCheckingBalance();
      final double amount = Double.parseDouble(the_amount);
      final double closing = open - amount;

      diff_text_savings.setText(formatter.format(closing));

      // this restarts the activity
      new Handler().postDelayed(new Runnable()
      {

         @Override
         public void run()
         {
            Person tempPerson = new Person();

            sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);

            ProtoTransactionData withdraw_transaction = new ProtoTransactionData();

            tempPerson.setFirstName(sharedPreferences.getString("f_name", null));
            tempPerson.setLastName(sharedPreferences.getString("l_name", null));
            tempPerson.setSavings(sharedPreferences.getBoolean("has_savings", false));
            tempPerson.setChecking(sharedPreferences.getBoolean("has_checking", false));
            tempPerson.setAccountNumber(sharedPreferences.getInt("acct_num", -1));
            tempPerson.setCheckingBalance(closing);
            tempPerson.setSavingsBalance(sharedPreferences.getFloat("save_bal", -1));

            Intent myIntent = new Intent(SavingsActivity.this, SavingsActivity.class);

            myIntent.putExtra("the_user", tempPerson);
            dialog.dismiss();

            // setup the transaction for DB storage
            withdraw_transaction.setTransType(WITHDRAW);
            withdraw_transaction.setAcctType("Checking");
            withdraw_transaction.setAmount(amount);
            withdraw_transaction.setOpenBalance(open);
            withdraw_transaction.setClosingBalance(closing);

            the_db.addTransaction(withdraw_transaction);

            // to ensure balance is current when user turns off the app
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("chrck_bal", (float) tempPerson.getCheckingBalance());
            editor.apply();
            startActivity(myIntent);

            //kills previous activity
            finish();

         }
      }, 1500);
   } // end withdraw

   private void erase()
   {
      sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);
      sharedPreferences.edit().clear().commit();

      // clears entire db
      the_db.clearDatabase();

      // this restarts the SavingsActivity and goes back to the title activity
      new Handler().postDelayed(new Runnable()
      {
         @Override
         public void run()
         {

            Intent titleIntent = new Intent(SavingsActivity.this, TitleActivity.class);
            startActivity(titleIntent);

            //kills previous activity
            finish();

         }
      }, 1500);

   } // end erase

} // end SavingsActivity

