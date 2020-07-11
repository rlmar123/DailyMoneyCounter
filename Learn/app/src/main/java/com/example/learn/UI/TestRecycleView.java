package com.example.learn.UI;

import android.app.AlertDialog;
import android.content.Context;
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

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position)
   {
      ProtoTransactionData some_item = test_item_list.get(position);

      holder.open_bal.setText("Opening Balance : " + some_item.getOpenBal());
      holder.closing_bal.setText("Closing Balance : " + some_item.getClosingBal());
      holder.amount.setText("Amount : " + some_item.getAmount());
      holder.date.setText("01/06/1980");


   }

   /////pick up from here

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
      public TextView date = null;


   //   public Button editButton = null;
   //   public Button deleteButton = null;

      public int id;

      // ViewHolder Constructor
      public ViewHolder(@NonNull View itemView, Context the_ctx)
      {
         super(itemView);
         the_context = the_ctx;

         open_bal = itemView.findViewById(R.id.first);
         closing_bal = itemView.findViewById(R.id.third);
         amount = itemView.findViewById(R.id.second);
         date = itemView.findViewById(R.id.fourth);


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
