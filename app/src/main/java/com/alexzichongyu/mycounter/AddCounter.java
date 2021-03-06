/**
 * AddCounter Class
 * Version 1.0
 * Created by AlexZichongYu on 2017-09-29.
 *
 * Copyright notice: this project has been created by Alex Zichong Yu for assignment purpose for Comput 301 in year 2017, all rights reserved.
 */

package com.alexzichongyu.mycounter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class AddCounter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_counter);

        Button save_new_counter = (Button) findViewById(R.id.done_adding);
                save_new_counter.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // we need to get information from the add new counter page
                        EditText editName = (EditText) findViewById(R.id.editName);
                        EditText editInitial = (EditText) findViewById(R.id.editInitial);
                        EditText editComment = (EditText) findViewById(R.id.editComment);



                        String name = editName.getText().toString();
                        String initial = editInitial.getText().toString();
                        String comment = editComment.getText().toString();
                        String current = initial;


                // check the input make sure all entering is valid
                if( name.isEmpty() ) {
                    editName.setError("Please enter your counter name");
                }


                if( Integer.parseInt(initial) == 0) {
                    editInitial.setError("Please set your initial value ");

                }

                Intent intent = new Intent();
                intent.putExtra("name", name);
                intent.putExtra("initial", initial);
                intent.putExtra("comment", comment);
                intent.putExtra("current", current);
                setResult(RESULT_OK, intent);
                finish();

            }

        });
    }
}