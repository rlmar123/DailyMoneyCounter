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

   private EditText name_field = null;
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
          //  createOpenPopupDialog();
         Intent the_intent = new Intent(TitleActivity.this, MainActivity.class);

        startActivity(the_intent);

         }
      });



   } // end onCreate


   private void createOpenPopupDialog()
   {
        builder = new AlertDialog.Builder(this);
        View open_view = getLayoutInflater().inflate(R.layout.open_pop_up, null);

      name_field = open_view.findViewById(R.id.name_text);
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

            Toast.makeText(TitleActivity.this, "You fixed it!!!! ", Toast.LENGTH_LONG).show();

         }
      });

   }

} // end TitleActivity
