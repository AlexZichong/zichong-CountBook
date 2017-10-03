/**
 * MainActivity Class
 * Version 1.0
 * Created by AlexZichongYu on 2017-09-29.
 *
 * Copyright notice: this project has been created by Alex Zichong Yu for assignment purpose for Comput 301 in year 2017, all rights reserved.
 */


package com.alexzichongyu.mycounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

/**
 * This is a CountBook App, allows user add, delete, view and modify the counters
 * The MainActivity send the array list to the adapter
 * Does array list update when modification happens
 */

public class MainActivity extends AppCompatActivity {


    public ListView listView;
    private int totalCounter = 0;
    static ArrayList<myCounter> mycounters = new ArrayList<>();

    private CounterAdapter cAdapter = null;

    /**
     * We get the list view here and send it to the listview adapter in order to displat the list of counters
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.counter_list);
        cAdapter = new CounterAdapter(this, mycounters);
        listView.setAdapter(cAdapter);


        /**
         * Using floating action button to start the activity to the add new counter class
         */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start to add counter
                Intent intent = new Intent(MainActivity.this, AddCounter.class);
                startActivityForResult(intent,2);  // expecting returning values from the activity

            }
        });
    }


    /**
     * The receving end of startActivityForResult
     * we will expecting the newly added counter and modified counter to display here
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        // When request code is 1, it gives back the modified data we started activity in the CounterAdapter class
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                Bundle bundleEdit = data.getBundleExtra("result");
                Integer decision = bundleEdit.getInt("decision");
                Integer position = bundleEdit.getInt("position");
                int modifyPosition = position;

                // if decision is one we delete the counter
                if (decision == 1) {

                    cAdapter.remove(modifyPosition);

                }

                // when we get decision = 2 that means user clicked save button on the modify page
                if (decision == 2) {
                    Integer changedInitial = bundleEdit.getInt("initial");
                    Integer changedCurrent = bundleEdit.getInt("current");
                    String changedName = bundleEdit.getString("name");
                    String changedComment = bundleEdit.getString("comment");

                    myCounter modifiedCounter = mycounters.get(modifyPosition);

                    modifiedCounter.setCurrentValue(changedCurrent);
                    modifiedCounter.setComment(changedComment);
                    cAdapter.replace(modifyPosition,modifiedCounter);
                }
                //cAdapter.notifyDataSetChanged();
            }
        }

        // When request code = 2 means this the the result get get back from the add new activity
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                String newValue = data.getStringExtra("initial");
                String newName = data.getStringExtra("name");
                String newComment = data.getStringExtra("comment");
                String newCurrent = data.getStringExtra("current");


                // new counter object
                myCounter new_newCounter = new myCounter(newName, Integer.parseInt(newValue), Integer.parseInt(newCurrent), newComment, new Date());

                // add it to the arrary list
                mycounters.add(new_newCounter);
                //ArrayList<myCounter> newList = new ArrayList<myCounter>();
                //newList.addAll(mycounters);

                // refresh

                //cAdapter.refresh(newList);
                cAdapter.notifyDataSetChanged();
            }
        }
    }


    // this part was here when the new activity layout generated

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count_book, menu);
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
