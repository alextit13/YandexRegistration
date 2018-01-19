package com.acherniakovich.android;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.acherniakovich.android.docs.PhotoDocs;

public class FirstScreen extends AppCompatActivity {

    private EditText edit_text_number;
    private ImageButton button_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        init();
    }

    private void init() {
        edit_text_number = (EditText)findViewById(R.id.edit_text_number);
        button_number = (ImageButton)findViewById(R.id.button_number);

        button_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterNumber();
            }
        });
    }

    private void enterNumber() {
        if (!edit_text_number.getText().toString().equals("+7")){
            // go to next screen
            Intent intent = new Intent(FirstScreen.this, PhotoDocs.class);
            intent.putExtra("number",edit_text_number.getText().toString());
            startActivity(intent);
        }else{
            Snackbar.make(button_number,"Введите номер телефона", BaseTransientBottomBar.LENGTH_SHORT).show();
        }
    }
}
