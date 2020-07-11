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

import com.example.learn.R;

import java.util.List;

public class RecyclerViewAdapt extends RecyclerView.Adapter<RecyclerViewAdapt.ViewHolder> {
   private Context the_context = null;
   private List<String> the_item_list = null;

   //to build alert dialog
   private AlertDialog.Builder confirm_builder = null;
   private AlertDialog confirm_dialog = null;

   private LayoutInflater the_inflater = null;

   //confrimation_pop_up.xml widget variables
   private Button the_no_button = null;
   private Button the_yes_button = null;

   public RecyclerViewAdapt(Context context, List<String> the_list)
   {
      the_context = context;
      the_item_list = the_list;
   }

   @NonNull
   @Override
   public RecyclerViewAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
   {
      //connection to my_list_row.xml
      View the_view = LayoutInflater.from(the_context).inflate(R.layout.trans_row, parent, false);

      return new ViewHolder(the_view, the_context);
   }

   //this is how the list populates as you scroll on and off the screen
   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position)
   {
      String some_item = the_item_list.get(position);

      holder.itemName.setText("Item");
      holder.itemColor.setText("Color You");
      holder.itemQuantity.setText("Quantity: ");
      holder.itemSize.setText("Size: ");
      holder.dateAdded.setText("Date: Myshehehe");

   }

   @Override
   public int getItemCount() {
      return the_item_list.size();
   }


   //magic happens here
   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      public TextView itemName = null;
      public TextView itemQuantity = null;
      public TextView itemColor = null;
      public TextView itemSize = null;
      public TextView dateAdded = null;

      public Button editButton = null;
      public Button deleteButton = null;

      public int id;

      // ViewHolder Constructor
      public ViewHolder(@NonNull View itemView, Context the_ctx)
      {
         super(itemView);
         the_context = the_ctx;

         itemName = itemView.findViewById(R.id.item_name);
         itemQuantity = itemView.findViewById(R.id.item_quantity);
         itemColor = itemView.findViewById(R.id.color_text);
         itemSize = itemView.findViewById(R.id.item_size);
         dateAdded = itemView.findViewById(R.id.date_item_added);

         editButton = itemView.findViewById(R.id.the_edit_button);
         deleteButton = itemView.findViewById(R.id.the_delete_button);


         editButton.setOnClickListener(this);
         deleteButton.setOnClickListener(this);
      }

      @Override
      public void onClick(View v) {

         //null

      }
   }

} //end Recycler
