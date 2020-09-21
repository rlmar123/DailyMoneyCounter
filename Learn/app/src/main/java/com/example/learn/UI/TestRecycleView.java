package com.example.learn.UI;

import android.app.AlertDialog;
import android.content.Context;
import android.icu.text.DateFormat;
import android.icu.text.NumberFormat;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learn.Model.ProtoTransactionData;
import com.example.learn.Model.TransactionData;
import com.example.learn.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TestRecycleView extends RecyclerView.Adapter<TestRecycleView.ViewHolder>
{

   private Context the_context = null;
   private List<ProtoTransactionData> test_item_list = null; //cached copy of nodo items

   //to build alert dialog
   private AlertDialog.Builder confirm_builder = null;
   private AlertDialog confirm_dialog = null;

   private LayoutInflater the_inflater = null;

   //confirmation_pop_up.xml widget variables
   private Button the_no_button = null;
   private Button the_yes_button = null;

   public TestRecycleView(Context context, List<ProtoTransactionData> the_list)
   {
      the_context = context;
      test_item_list = the_list;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
   {
      //connection to my_list_row.xml
      View test_view = LayoutInflater.from(the_context).inflate(R.layout.trans_row_test, parent, false);

      return new TestRecycleView.ViewHolder(test_view, the_context);
   }

   //this is how the list populates as you scroll on and off the screen
   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position)
   {


      // to format into dollar format
      NumberFormat formatter = NumberFormat.getCurrencyInstance();

      ProtoTransactionData some_item = test_item_list.get(position);

      holder.open_bal.setText("Opening Balance : " + formatter.format(some_item.getOpenBal()));
      holder.amount.setText("Amount : " + formatter.format(some_item.getAmount()));
      holder.closing_bal.setText("Closing Balance : " + formatter.format(some_item.getClosingBal()));
      holder.trans_type.setText("Amount : " + formatter.format(some_item.getAmount()));
      holder.trans_type.setText(some_item.getTransType());
      holder.date.setText("Date : ");
      holder.description.setText("Description : ");
   }

   public void setNoDos(List<ProtoTransactionData> trans_data)
   {
      test_item_list = trans_data;
      notifyDataSetChanged();
   }


   @Override
   public int getItemCount() {
      return test_item_list.size();
   }

   //magic happens here
   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
   {
      public TextView open_bal = null;
      public TextView closing_bal = null;
      public TextView amount = null;
      public TextView trans_type = null;
      public TextView date = null;
      public TextView description = null;

      public int id;

      // ViewHolder Constructor
      public ViewHolder(@NonNull View itemView, Context the_ctx)
      {
         super(itemView);
         the_context = the_ctx;

         open_bal = itemView.findViewById(R.id.first);
         amount = itemView.findViewById(R.id.second);
         closing_bal = itemView.findViewById(R.id.third);
         trans_type = itemView.findViewById(R.id.fourth);
         date = itemView.findViewById(R.id.fifth);
         description = itemView.findViewById(R.id.sixth);



    /*   editButton = itemView.findViewById(R.id.the_edit_button);
        deleteButton = itemView.findViewById(R.id.the_delete_button);


         editButton.setOnClickListener(this);
         deleteButton.setOnClickListener(this); */
      }

      @Override
      public void onClick(View v)
      {
        // null
      }
   } // end viewholder

} // end TestRecyclerView
