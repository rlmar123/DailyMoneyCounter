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

            if((!first_name_field.getText().toString().isEmpty()) && (!last_name_field.getText().toString().isEmpty()) && (!savings_account_field.getText().toString().isEmpty()) && (!checking_account_field.getText().toString().isEmpty()))
            {
               String first = first_name_field.getText().toString().trim();
               String last  = last_name_field.getText().toString().trim();


               String savings_response = savings_account_field.getText().toString().trim();
               String checking_response = checking_account_field.getText().toString().trim();

               user.setFirstName(first);
               user.setLastName(last);

               if((savings_response.charAt(0) == 'Y') || (savings_response.charAt(0) == 'y'))
               {
                  user.setSavings(true);

               }

               else if((savings_response.charAt(0) == 'N') || (savings_response.charAt(0) == 'n'))
               {
                  user.setSavings(false);


               }

               Toast.makeText(TitleActivity.this, user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_LONG).show();
            }

            else
               Toast.makeText(TitleActivity.this, "Missing a field", Toast.LENGTH_LONG).show();


         }
      });

   }

} // end TitleActivity
