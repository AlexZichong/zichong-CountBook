/**
 * EditCounter Class
 * Version 1.0
 * Created by AlexZichongYu on 2017-09-29.
 *
 * Copyright notice: this project has been created by Alex Zichong Yu for assignment purpose for Comput 301 in year 2017, all rights reserved.
 */

package com.alexzichongyu.mycounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;


/**
 * This is the part we edit the counter
 * it will get the value we have for each counter and display on the edit counter layout
 */
public class EditCounter extends AppCompatActivity {
    private Integer current, initial, position;
    private String comment, name;

    /**
     * getting values from intent
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);
        /**
         * @param bundle The bunde includes name, initial value, comment, and the array position
         */

        final Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null) {
            name = bundle.getString("name");
            current = bundle.getInt("current");
            comment = bundle.getString("comment");
            initial = bundle.getInt("initial");
            position = bundle.getInt("position");

            // dispalying all the information we have on the UI


            TextView textViewName = (TextView) findViewById(R.id.textView_name);
            textViewName.setText(name);

            TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
            textViewCurrent.setText(current.toString());

            TextView textViewInitial = (TextView) findViewById(R.id.textView_initialValue);
            textViewInitial.setText(initial.toString());

            final EditText textViewComment = (EditText) findViewById(R.id.editText_comment);
            textViewComment.setText(comment);

            /**
             * Three buttons we have on the par increment, decrement and reset the current value
             * @param current The current value changes when user hits different button
             */
            // increment button
            Button addButton = (Button) findViewById(R.id.button_add);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current++;

                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());

                }
            });

            // decrement button
            Button subButton = (Button) findViewById(R.id.button_subtract);
            subButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current--;

                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());
                }
            });

            // reset button
            Button resetButton = (Button) findViewById(R.id.button_reset);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current = initial;

                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());

                }
            });

            final Integer currentNew = current;

            /**
             * put all values in a bundle sending it back to MainActivity as a intent
             * this case user hit the save button
             */

            Button edit_counter = (Button) findViewById(R.id.button_save);
            edit_counter.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String changedComment = textViewComment.getText().toString();
                    Integer decision = 2;  // Main Activity will use decision to know weather its a delete or a save
                    Intent returnIntent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putInt("decision", decision);
                    bundle.putInt("position", position);
                    bundle.putString("name", name);
                    bundle.putInt("current",current);
                    bundle.putInt("initial",initial);
                    bundle.putString("comment",changedComment);


                    returnIntent.putExtra("result", bundle);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            });

            /**
             * When user hit the delete button, we pack everything and send it back with a different decision value
             */
            Button delete_counter = (Button) findViewById(R.id.delete_button);
            delete_counter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Integer decision = 1;
                    Intent returnIntent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putInt("decision", decision);
                    bundle.putInt("position", position);
                    returnIntent.putExtra("result", bundle);
                    setResult(RESULT_OK, returnIntent);
                    finish();

                }
            });


        }
    }

}
