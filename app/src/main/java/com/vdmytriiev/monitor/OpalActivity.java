package com.vdmytriiev.monitor;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.CaptureActivity;

public class OpalActivity extends AppCompatActivity {
    private TextView user_text3;
    private TextView user_test2_opal;
    private TextView json_l10;
    private Button l10_opal;
    private Button l12_opal;
    private Button burnin_opal;
    private Button l123_opal;
    private ScrollView scrol_server_opal;
    private SearchView search_server_opal;
    private static final int REQUEST_CODE_SCAN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opal);
        Button Buttonl10 = findViewById(R.id.l10_opal);
        Button Buttonl12 = findViewById(R.id.l12_opal);
        Button Buttonburnin = findViewById(R.id.burnin_opal);
        Button Buttonl123 = findViewById(R.id.l123_opal);
        TextView myTextView3 = findViewById(R.id.user_test3_opal);
        SearchView mySearch = findViewById(R.id.search_server_opal);

            Buttonl10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTextView3.setText("OPAL L10 TEST");
                    Intent intent = new Intent(OpalActivity.this, CaptureActivity.class);
                    intent.setAction(Intents.Scan.ACTION);
                    intent.putExtra(Intents.Scan.MODE, Intents.Scan.QR_CODE_MODE);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
            });
        Buttonl12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView3.setText("OPAL L12 TEST");
            }
        });
        Buttonburnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView3.setText("OPAL BURN-IN TEST");
            }
        });
        Buttonl123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTextView3.setText("OPAL L12-3 TEST");
            }
        });
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            String contents = data.getStringExtra(Intents.Scan.RESULT);
            // Виконуємо пошук за допомогою вмісту штрих-коду
        }
    }

}