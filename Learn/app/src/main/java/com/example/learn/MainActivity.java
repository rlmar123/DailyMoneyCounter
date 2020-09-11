package com.example.learn;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.learn.Model.Person;
import com.example.learn.Model.Transaction;
import com.example.learn.UI.RecyclerViewAdapt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;



    //TEST
    Person test = null;
    Transaction test_transaction = null;

    //activity_main.xml widget variables
    private RecyclerView recycler_view = null;
    private RecyclerViewAdapt recycler_adapter= null;
    private List<String> our_item_list = null;

  //  private Button deposit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   //     Toolbar toolbar = findViewById(R.id.toolbar);
   //     setSupportActionBar(toolbar);

  //      test = new Person();
 //       test_transaction = new Transaction();

  //      mediaPlayer = new MediaPlayer();
    //    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.test);

        our_item_list = new ArrayList<String>();

        // test data
        our_item_list.add("Yes");
        our_item_list.add("pie");
        our_item_list.add("fries");
        our_item_list.add("pizza");
        our_item_list.add("shakes");
        our_item_list.add("ketchup");
        our_item_list.add("cotton candy");
        our_item_list.add("soda");





        //connect to activity_main.xml widget
        recycler_view = findViewById(R.id.recycler);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));


        //setup recycler_adapter
        recycler_adapter = new RecyclerViewAdapt(this, our_item_list);
        recycler_view.setAdapter(recycler_adapter);


        //keeps data up to date
        recycler_adapter.notifyDataSetChanged();


        // FABSS
        FloatingActionButton leftFab = findViewById(R.id.left_fab);
        leftFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                createSubtractPopupDialog();
            }
        });

        // FABSS
        FloatingActionButton midFab = findViewById(R.id.mid_fab);
        midFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nextActivity();
            }
        });

        FloatingActionButton rightFab = findViewById(R.id.right_fab);
        rightFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createAddPopupDialog();

            }
        });



    } // end on Create

    private void createAddPopupDialog() {


        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.add_pop_up, null);

        for(String temp : our_item_list)
        {
            Log.d("list view!!! ", "adding " + temp);
        }



        builder.setView(view);
        dialog = builder.create();// creating our dialog object
        dialog.show();// important step!



    }

    private void createSubtractPopupDialog()
    {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.subtract_pop_up, null);

        for(String temp : our_item_list)
        {
            Log.d("list view!!! ", "subtract " + temp);
        }



        builder.setView(view);
        dialog = builder.create();// creating our dialog object
        dialog.show();// important step!



    }

    private void nextActivity()
    {
        Intent the_intent = new Intent(MainActivity.this, SavingsActivity.class);
        startActivity(the_intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
