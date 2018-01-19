package com.acherniakovich.android.docs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

import com.acherniakovich.android.R;

import static android.icu.lang.UProperty.INT_START;

public class Info extends AppCompatActivity {

    private TextView q_1,q_2, q_3, q_4, q_5;
    private TextView a_1,a_2,a_3,a_4,a_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        init();
        links();

    }

    private void init() {
        q_1 = (TextView)findViewById(R.id.q_1);
        q_2 = (TextView)findViewById(R.id.q_2);
        q_3 = (TextView)findViewById(R.id.q_3);
        q_4 = (TextView)findViewById(R.id.q_4);
        q_5 = (TextView)findViewById(R.id.q_5);

        a_1 = (TextView)findViewById(R.id.a_1);
        a_2 = (TextView)findViewById(R.id.a_2);
        a_3 = (TextView)findViewById(R.id.a_3);
        a_4 = (TextView)findViewById(R.id.a_4);
        a_5 = (TextView)findViewById(R.id.a_5);
    }

    private void links(){
        Linkify.addLinks(a_4,Linkify.WEB_URLS);
        a_4.setMovementMethod(LinkMovementMethod.getInstance());
        a_4.setLinksClickable(true);

        Linkify.addLinks(a_5,Linkify.WEB_URLS);
        a_5.setMovementMethod(LinkMovementMethod.getInstance());
        a_5.setLinksClickable(true);
    }
}
