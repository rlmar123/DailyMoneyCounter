package com.example.learn;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learn.Model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TitleActivity extends AppCompatActivity {

   // open_pop_up.xml widgets
   private Button test_button = null;
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

   private Person user = null;

   private String savings_response;
   private String checking_response;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_title);


      test_button = findViewById(R.id.test_button);



      test_button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v)
         {
            createOpenPopupDialog();
         //Intent the_intent = new Intent(TitleActivity.this, MainActivity.class);

       // startActivity(the_intent);

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
      checking_account_field  = open_view.findViewById(R.id.checking_answer);

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
            if((!first_name_field.getText().toString().isEmpty()) && (!last_name_field.getText().toString().isEmpty()) && (!savings_account_field.getText().toString().isEmpty()) && (!checking_account_field.getText().toString().isEmpty()))
            {
               //convert first and last name to string to insert into user object
               String first = first_name_field.getText().toString().trim();
               String last = last_name_field.getText().toString().trim();


               savings_response = savings_account_field.getText().toString().trim();
               checking_response = checking_account_field.getText().toString().trim();

               //set object first and last name
               user.setFirstName(first);
               user.setLastName(last);

               if(accountFieldValidator())
               {

                  // test savings
                  if ((savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y'))
                     user.setSavings(true);
                    // Toast.makeText(TitleActivity.this, "savings is Yes checking is yes", Toast.LENGTH_LONG).show();

                  else if ((savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n'))
                     user.setSavings(false);
                     //Toast.makeText(TitleActivity.this, "savings is Yes savings is yes", Toast.LENGTH_LONG).show();

          //        else
            //         Toast.makeText(TitleActivity.this, "Answer must be yes or no.... ", Toast.LENGTH_LONG).show();


                  // test checking
                  if ((checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y'))
                     user.setChecking(true);

                  else if ((checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n'))
                     user.setChecking(false);


           //       else
             //        Toast.makeText(TitleActivity.this, "Answer must be yes or no.... ", Toast.LENGTH_LONG).show();

               }

               // fields for checking and savings are NO GOOD
               else
                  Toast.makeText(TitleActivity.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_LONG).show();
            }


            // a field is missing
            else
               Toast.makeText(TitleActivity.this, "Missing a field", Toast.LENGTH_LONG).show();

         }
      });

   } // end createOpenPopupDialog


   private boolean accountFieldValidator()
   {

      boolean isGood = false;

      if(( (savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y') ) && ( (checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y') )){
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is Yes checking is yes", Toast.LENGTH_LONG).show();

      }

      else if ( ( (savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n') ) && ( (checking_response.charAt(0) == 'Y') || (checking_response.charAt(0) == 'y') ) ) {
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is no checking is yes", Toast.LENGTH_LONG).show();

      }

      else if ( ( (savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y') ) && ( (checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n') ) ){
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is Yes checking is no", Toast.LENGTH_LONG).show();

      }

      else if ( ( (savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n') ) && ( (checking_response.charAt(0) == 'N') || (checking_response.charAt(0) == 'n') ) ){
         isGood = true;
         Toast.makeText(TitleActivity.this, "savings is no checking is no", Toast.LENGTH_LONG).show();

      }

      return isGood;

   } //end accountFieldValidator



} // end TitleActivity
