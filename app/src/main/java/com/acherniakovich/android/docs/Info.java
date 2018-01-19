package com.acherniakovich.android.docs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;

import com.acherniakovich.android.R;

public class Info extends AppCompatActivity {

    private TextView    info_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        links();

    }

    private void init() {
        info_1 = (TextView) findViewById(R.id.text_1);
    }

    private void links(){
        Linkify.addLinks(info_1,Linkify.WEB_URLS);
        info_1.setMovementMethod(LinkMovementMethod.getInstance());
        info_1.setLinksClickable(true);
    }
}
