/**
 * CounterAdapter Class
 * Version 1.0
 * Created by AlexZichongYu on 2017-09-29.
 *
 * Copyright notice: this project has been created by Alex Zichong Yu for assignment purpose for Comput 301 in year 2017, all rights reserved.
 */

package com.alexzichongyu.mycounter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * CounterAdapter works like a projector to display each array item
 *
 */

public class CounterAdapter extends ArrayAdapter<myCounter> {
    private Context cContext;
    private ArrayList<myCounter> counterList = new ArrayList<myCounter>();

    /**
     *
     * @param context context ib the array list
     * @param list the array list itself
     */
    public CounterAdapter(@NonNull Context context, @LayoutRes ArrayList<myCounter> list) {

        super(context, 0, list);
        cContext = context;
        counterList = list;

    }

    @NonNull
    @Override

    public View getView( final int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
       // Context context = this.getContext();





        if (listItem == null) {

            listItem = LayoutInflater.from(cContext).inflate(R.layout.list_item, parent, false);

            // When user long click we pop out the alert dialog to double check if user want to delete the counter

            listItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(cContext, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(cContext);
                    }
                    builder.setTitle("Delete entry")
                            .setMessage("Are you sure you want to delete this entry?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    counterList.remove(position);

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    //Reference https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android

                    return false;
                }
            });

            /**
             * since we need to "project" each column of the array list we will have to send the value for each block to the the user interface
             */

            final myCounter currentCounter = counterList.get(position);

            TextView nameTextView = listItem.findViewById(R.id.textView_name);
            nameTextView.setText(currentCounter.getName());

            final Integer position_index = position;
            //nameTextView.setText(display_pos.toString());

            final TextView currentValueTextView = listItem.findViewById(R.id.textView_current);
            Integer value = currentCounter.getCurrentValue();

            currentValueTextView.setText(value.toString());

            TextView dateTextView = listItem.findViewById(R.id.textView_date);
            dateTextView.setText(currentCounter.currentDate());


            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(cContext, EditCounter.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",currentCounter.getName() );
                    bundle.putInt("current", currentCounter.getCurrentValue());
                    bundle.putString("comment",currentCounter.getComment());
                    bundle.putInt("position", position_index);
                    bundle.putInt("initial",currentCounter.getInitValue());
                    intent.putExtra("Bundle",bundle);
                    ((Activity)cContext).startActivityForResult(intent, 1);

                }
            });
        }

        return listItem;
    }

    public void remove(int position) {
        counterList.remove(position);
        this.notifyDataSetChanged();
    }

    public void replace(int position, myCounter counter) {
        counterList.set(position, counter);
        this.notifyDataSetChanged();
    }
}



//https://stackoverflow.com/questions/2265661/how-to-use-arrayadaptermyclass
//https://blog.mindorks.com/custom-array-adapters-made-easy-b6c4930560dd