package com.alexzichongyu.mycounter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class editCounter extends AppCompatActivity {
private Integer current, initial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_counter);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Bundle");
        if (bundle != null) {
            String name = bundle.getString("name");
            current = bundle.getInt("current");
            String comment = bundle.getString("comment");
            initial = bundle.getInt("initial");
            final Integer position = bundle.getInt("position");


            TextView textViewName = (TextView) findViewById(R.id.textView_name);
            textViewName.setText(name);

            TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
            textViewCurrent.setText(current.toString());

            TextView textViewInitial = (TextView) findViewById(R.id.textView_initialValue);
            textViewInitial.setText(initial.toString());

            EditText editTextComment = (EditText) findViewById(R.id.editText_changeComment);
            editTextComment.setText(comment);

            //volatile
            Button addButton = (Button) findViewById(R.id.button_add);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current++;
                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());

                }
            });


            Button subButton = (Button) findViewById(R.id.button_subtract);
            subButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current--;
                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());
                }
            });

            Button resetButton = (Button) findViewById(R.id.button_reset);
            resetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    current = initial;
                    TextView textViewCurrent = (TextView) findViewById(R.id.textView_currentValue);
                    textViewCurrent.setText(current.toString());
                }
            });


            Button edit_counter = (Button) findViewById(R.id.button_save);
            edit_counter.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    // we need to get information from the add new counter page

                    //EditText editComment = (EditText) findViewById(R.id.editComment);
                    //String comment = editComment.getText().toString();

                    Intent returnIntent = new Intent();
                    //Intent intent = new Intent(cContext, editCounter.class);
                    Bundle bundle = new Bundle();
                    //bundle.putString("comment", comment);
                    bundle.putInt("current", current);
                    bundle.putInt("position", position);

                    returnIntent.putExtra("result", bundle);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            });

        }
    }

}
