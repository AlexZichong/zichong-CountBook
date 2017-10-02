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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AlexZichongYu on 2017-09-29.
 */


public class CounterAdapter extends ArrayAdapter<myCounter> {
    private Context cContext;
    private ArrayList<myCounter> counterList = new ArrayList<myCounter>();
    //private int layoutId;


    public CounterAdapter(@NonNull Context context, /*int textViewResourceId,*/ @LayoutRes ArrayList<myCounter> list) {
        // super(context, textViewResourceId, items);
        super(context, 0, list);
        cContext = context;
        counterList = list;

        //this.context = context;
        //this.layoutId = textViewResourceId;
        //this.counter = items;

    }

    @NonNull
    @Override

    public View getView(final int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
       // Context context = this.getContext();

        if (listItem == null) {

            listItem = LayoutInflater.from(cContext).inflate(R.layout.list_item, parent, false);

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
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    // https://stackoverflow.com/questions/2115758/how-do-i-display-an-alert-dialog-on-android



                    return false;
                }
            });




            final myCounter currentCounter = counterList.get(position);

            TextView nameTextView = listItem.findViewById(R.id.textView_name);
            nameTextView.setText(currentCounter.getName());

            final TextView currentValueTextView = listItem.findViewById(R.id.textView_current);
            Integer value = currentCounter.getCurrentValue();
            currentValueTextView.setText(value.toString());

            TextView dateTextView = listItem.findViewById(R.id.textView_date);
            dateTextView.setText(currentCounter.currentDate());

            listItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(cContext, editCounter.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",currentCounter.getName() );
                    bundle.putInt("current", currentCounter.getCurrentValue());
                    //bundle.putString("comment",currentCounter.getComment());
                    bundle.putInt("position", position);
                    bundle.putInt("initial",currentCounter.getInitValue());
                    intent.putExtra("Bundle",bundle);
                    ((Activity)cContext).startActivityForResult(intent, 1);

                }

            });




        }



        return listItem;
    }

    public void refresh(ArrayList<myCounter> newList) {
        counterList.clear();
        counterList.addAll(newList);
        this.notifyDataSetChanged();
    }
}






/*
            //LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutId,parent,false);


            //Where to put it
            //RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder;
            viewHolder.showName = (TextView) convertView.findViewById(R.id.textView_name);
            viewHolder.showNumber = (TextView) convertView.findViewById(R.id.textView_current);
            viewHolder.showDate = (TextView) convertView.findViewById(R.id.textView_date);
            convertView.setTag(viewHolder);

            */


        /*else {
            viewHolder = (viewHolder) convertView.getTag();

        }  */

        //myCounter item = counter.get(position);






//https://stackoverflow.com/questions/2265661/how-to-use-arrayadaptermyclass
//https://blog.mindorks.com/custom-array-adapters-made-easy-b6c4930560dd