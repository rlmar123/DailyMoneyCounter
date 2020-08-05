package com.example.learn;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.learn.Data.OurDB;
import com.example.learn.Model.Person;
import com.example.learn.Model.ProtoTransactionData;
import com.example.learn.Model.TransactionData;
import com.example.learn.Model.TransactionDataViewModel;
import com.example.learn.UI.TestRecycleView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
   private double test_val = 675.98;
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
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getInt("acct_num", -1));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getFloat("chrck_bal", -1));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getFloat("save_bal", -1));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getBoolean("has_savings", false));
      Log.d("ProtoTransactionData_1", "From title activity " + sharedPreferences.getBoolean("has_checking", false));

      the_db = new OurDB(this);
      the_db.clearDatabase();

      our_item_list = new ArrayList<ProtoTransactionData>();
//      our_item_list = the_db.getAllTransactions();


      //the_db.clearDatabase();
      our_item_list = the_db.getAllTransactions();


//     our_item_list.add(new_transaction);
  //   our_item_list.add(next_new_transaction);

 //     our_item_list.add(txt_transaction);


      //connect to activity_main.xml widget
      recycler_view = findViewById(R.id.recycler);
      recycler_view.setHasFixedSize(true);
      recycler_view.setLayoutManager(new LinearLayoutManager(this));


      //setup recycler_adapter
      test_adapter = new TestRecycleView(this, our_item_list);
      recycler_view.setAdapter(test_adapter);

      //keeps data up to date
      test_adapter.notifyDataSetChanged();

      // FABSS
      FloatingActionButton leftFab = findViewById(R.id.left_fab);
      leftFab.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            createSubtractPopupDialog();
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

      // make connection to add_pop_up.xml widgets
      balance_text_savings = view.findViewById(R.id.balance_text);
      deposit_text_savings = view.findViewById(R.id.deposit_text);
      sum_text_savings = view.findViewById(R.id.sum_text);

      confirm_button_savings = view.findViewById(R.id.add_confirm_button);
      cancel_button_savings = view.findViewById(R.id.add_cancel_button);

      // we will use person savings bal here!!!!
      balance_text_savings.setText("Balance : " +
              "" + Double.toString(the_user.getCheckingBalance()));
      deposit_text_savings.setText("2 billion");
      sum_text_savings.setText("10 Trillion");


      builder.setView(view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!


      confirm_button_savings.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
          //  Toast.makeText(SavingsActivity.this, "CLICK CLICK CLICK", Toast.LENGTH_LONG).show();
           deposit();
         }
      });

      cancel_button_savings.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dialog.dismiss();
         }
      });

/* here we we get the data from the add pop up widgets
*  after we make connection to widgets, we can extract the data and
* create the object
*
*
*
* */




   }

   private void createSubtractPopupDialog()
   {

      builder = new AlertDialog.Builder(this);
      View view = getLayoutInflater().inflate(R.layout.subtract_pop_up, null);

      withdrawBal_text_savings = view.findViewById(R.id.withdraw_balance_text);
      withdraw_text_savings = view.findViewById(R.id.withdraw_text);
      diff_text_savings = view.findViewById(R.id.difference_text);

      confirm_button_savings = view.findViewById(R.id.add_confirm_button);
      cancel_button_savings = view.findViewById(R.id.add_cancel_button);


      withdrawBal_text_savings.setText("100 million");
      withdraw_text_savings.setText("----2 billion");
      diff_text_savings.setText("2310 Trillion");

      builder.setView(view);
      dialog = builder.create();// creating our dialog object
      dialog.show();// important step!

      confirm_button_savings.setOnClickListener(new View.OnClickListener()
      {
         @Override
         public void onClick(View v)
         {
            Toast.makeText(SavingsActivity.this, "RING RING RING", Toast.LENGTH_LONG).show();
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
      final String DEPOSIT = "Deposit";

      ProtoTransactionData test_new_transaction = new ProtoTransactionData("TEST_DEPOSIT", "TEST_ACCT", 0, 1000,test_val);
    //  ProtoTransactionData test_next_new_transaction = new ProtoTransactionData(2,"NEW_TEST_DEPOSIT", "NEW_TEST_ACCT", 0, 1000,1000.00);

      //test data
 //     our_item_list.add(test_new_transaction);
        the_db.addTransaction(test_new_transaction);


      new Handler().postDelayed(new Runnable()
      {

         @Override
         public void run()
         {
            Person tempPerson = new Person();

            sharedPreferences = getSharedPreferences(MESSAGE_ID, MODE_PRIVATE);


            /*
               We need to create a tempPerson objrct to send with the intent. This is
               why the activity is crashing I believe. The activity never receives an intent
               on line 745 and 75...


             */

            tempPerson = new Person();

            tempPerson.setFirstName(sharedPreferences.getString("f_name", null));
            tempPerson.setLastName(sharedPreferences.getString("l_name", null));
            tempPerson.setCheckingBalance(sharedPreferences.getFloat("chrck_bal", -1));


            Intent myIntent = new Intent(SavingsActivity.this, SavingsActivity.class);
            myIntent.putExtra("the_user", tempPerson);
            dialog.dismiss();



            startActivity(myIntent);

            Toast.makeText(SavingsActivity.this, "TWO obj created", Toast.LENGTH_LONG).show();
            //kills previous activity
            finish();

         }
      }, 1500);


   }

}

