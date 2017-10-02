package com.alexzichongyu.mycounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class CountBookActivity extends AppCompatActivity {


    public ListView listView;
    private int totalCounter = 0;
    private ArrayList<myCounter> mycounters = new ArrayList<>();
    private CounterAdapter cAdapter = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TEST DATA
        for (int i = 0; i < 2; i++) {
            String name = new String(new Integer(i).toString());
            mycounters.add(new myCounter(name, 10, name, new Date()));
        }
        Log.d("first step", "1");


        // list view part, not sure if its gonna work tho
        listView = (ListView) findViewById(R.id.counter_list);
        cAdapter = new CounterAdapter(this, mycounters);
        listView.setAdapter(cAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start to add counter
                Intent intent = new Intent(CountBookActivity.this, addCounter.class);
                startActivityForResult(intent,2);

            }
        });
    }




    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        if (requestCode == 1) {
            if (requestCode == RESULT_OK) {
                Bundle bundleEdit = data.getBundleExtra("bundle");
                //String changedComment = bundleEdit.getString("comment");
                Integer changedCurrent = bundleEdit.getInt("current");

                Integer position = bundleEdit.getInt("position");
                myCounter tempCounter = mycounters.get(position);
                tempCounter.setCurrentValue(changedCurrent);
                mycounters.set(position,tempCounter);
                //((BaseAdapter)cAdapter).NotifyDataSetChanged();
                cAdapter.refresh(mycounters);




            }

        }


        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                String newValue = data.getStringExtra("initial");
                String newName = data.getStringExtra("name");
                String newComment = data.getStringExtra("comment");


                // new counter object
                myCounter new_newCounter = new myCounter(newName, Integer.parseInt(newValue), newComment, new Date());

                // add it to the arrary list
                mycounters.add(new_newCounter);
                ArrayList<myCounter> newList = new ArrayList<myCounter>();
                newList.addAll(mycounters);

                // refresh

                cAdapter.refresh(newList);







                }
        }



    }


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
