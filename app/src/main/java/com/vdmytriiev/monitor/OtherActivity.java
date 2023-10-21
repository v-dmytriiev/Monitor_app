package com.vdmytriiev.monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private TextView user_text4_other;
    private TextView user_test2_other;
    private TextView json_l10;
    private Button l10_other;
    private Button l12_other;
    private Button burnin_other;
    private Button l123_other;
    private ScrollView scrol_server_other;
    private SearchView search_server_other;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Button Buttonl10 = findViewById(R.id.l10_other);
        Button Buttonl12 = findViewById(R.id.l12_other);
        Button Buttonburnin = findViewById(R.id.burnin_other);
        Button Buttonl123 = findViewById(R.id.l123_other);
        TextView myTextView4 = findViewById(R.id.user_test2_other);
        SearchView mySearch = findViewById(R.id.search_server_other);


        Buttonl10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView4.setText("OTHER L10 TEST");
            }
        });
        Buttonl12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView4.setText("OTHER L12 TEST");
            }
        });
        Buttonburnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView4.setText("OTHER BURN-IN TEST");
            }
        });
        Buttonl123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView4.setText("OTHER L12-3 TEST");
            }
        });
    }
}