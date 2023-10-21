package com.vdmytriiev.monitor;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BahubaliActivity extends AppCompatActivity {
    private TextView user_test2_bahubali;
    private TextView json_l10;
    private Button l10_bahubali;
    private Button l12_bahubali;
    private Button burnin_bahubali;
    private Button l123_bahubali;
    private ScrollView scrol_server_bahubali;
    private SearchView search_server_bahubali;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahubali);
        Button Button_l10 = findViewById(R.id.l10_bahubali);
        Button Button_l12 = findViewById(R.id.l12_bahubali);
        Button ButtonBurn_in = findViewById(R.id.burnin_bahubali);
        Button Button_l123 = findViewById(R.id.l123_bahubali);
        TextView myTextView = findViewById(R.id.user_test2_bahubali);
        SearchView mySearch = findViewById(R.id.search_server_bahubali);
        LinearLayout linearLayout_bahubali = findViewById(R.id.linearLayout1_bahubali);
        Button SearchButton = findViewById(R.id.scan_server);

        Button_l10.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                myTextView.setText("BAHUBALI L10 TEST");
                new BahubaliActivity.GetDataL10().execute();
            }
        });
        Button_l12.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                myTextView.setText("BAHUBALI L12 TEST");
                new BahubaliActivity.GetDataL12().execute();
            }

        });
        ButtonBurn_in.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                myTextView.setText("BAHUBALI BURN-IN TEST");
                new BahubaliActivity.GetDataBurnin().execute();
            }

        });
        Button_l123.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                myTextView.setText("BAHUBALI L12-3 TEST");
                new BahubaliActivity.GetDataL123().execute();
            }

        });
        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(BahubaliActivity.this);
                integrator.setCaptureActivity(CaptureActivity.class);
                integrator.setOrientationLocked(true);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("To scan the barcode, point the camera at it!");
                integrator.initiateScan();
            }
        });

        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText.toLowerCase(Locale.getDefault());


                String scannedText = getIntent().getStringExtra("scanned_text");

                for (int i = 0; i < linearLayout_bahubali.getChildCount(); i++) {
                    Button button = (Button) linearLayout_bahubali.getChildAt(i);
                    String buttonText = button.getText().toString().toLowerCase(Locale.getDefault());
                    if (buttonText.contains(text) || buttonText.contains(scannedText)) {
                        button.setVisibility(View.VISIBLE);
                    } else {
                        button.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });


        mySearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText.toLowerCase(Locale.getDefault());
                for (int i = 0; i < linearLayout_bahubali.getChildCount(); i++) {
                    Button button = (Button) linearLayout_bahubali.getChildAt(i);
                    String buttonText = button.getText().toString().toLowerCase(Locale.getDefault());
                    if (buttonText.contains(text)) {
                        button.setVisibility(View.VISIBLE);
                    } else {
                        button.setVisibility(View.GONE);
                    }
                }
                return true;
            }
        });
    }

    private class GetDataL10 extends AsyncTask<Void, Void, String> {
        OkHttpClient client = new OkHttpClient();

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(Void... voids) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("Working...");
            try {
                String url = "https://raw.githubusercontent.com/v-dmytriiev/data/main/data.json";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("BAHUBALI L10 TEST");
            LinearLayout linearLayout_bahubali = findViewById(R.id.linearLayout1_bahubali);
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray servers = json.getJSONArray("data");


                    linearLayout_bahubali.removeAllViews();


                    for (int i = 0; i < servers.length(); i++) {
                        JSONObject server = servers.getJSONObject(i);
                        String serialnumber = server.getString("serialnumber");
                        String currentstate = server.getString("currentstate");
                        String status = server.getString("status");
                        String location = server.getString("location");
                        String ipaddress = server.getString("ipaddress");
                        String starttime = server.getString("starttime");

                        Button button = new Button(BahubaliActivity.this);
                        button.setText(serialnumber + "  " + location + "  " + currentstate + "  " + status);
                        if (status.equals("FAILED")) {
                            button.setBackgroundResource(R.drawable.button_failed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("PASSED")) {
                            button.setBackgroundResource(R.drawable.button_passed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("TESTING")) {
                            button.setBackgroundResource(R.drawable.button_testing);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("OFFLINE")) {
                            button.setBackgroundResource(R.drawable.button_offline);
                            button.setTextColor(Color.WHITE);
                        } else {
                            button.setBackgroundResource(R.drawable.button_other);
                            button.setTextColor(Color.WHITE);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyDialogStyle);
                                builder.setMessage("\nS/N: " + serialnumber + "\nLocation: " + location + "\nStage: " + currentstate
                                        + "\nStatus: " + status + "\nIP: " + ipaddress + "\nStart Time: " + starttime);

                                builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                if (status.equals("FAILED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_failed);
                                    dialog.show();
                                } else if (status.equals("TESTING")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_testing);
                                    dialog.show();
                                } else if (status.equals("OFFLINE")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_server_offline);
                                    dialog.show();
                                } else if (status.equals("PASSED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_passed);
                                    dialog.show();
                                } else {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_other);
                                    dialog.show();
                                }
                            }
                        });
                        linearLayout_bahubali.addView(button);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(BahubaliActivity.this, "Unable to load data. Check the URL and try again.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @SuppressLint("StaticFieldLeak")
    private class GetDataL12 extends AsyncTask<Void, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(Void... voids) {

            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("Working...");
            try {
                String url = "https://raw.githubusercontent.com/v-dmytriiev/data/main/data.json";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("BAHUBALI L12 TEST");
            LinearLayout linearLayout_debug = findViewById(R.id.linearLayout1_bahubali);
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray servers = json.getJSONArray("data");


                    linearLayout_debug.removeAllViews();

                    for (int i = 0; i < servers.length(); i++) {
                        JSONObject server = servers.getJSONObject(i);
                        String serialnumber = server.getString("serialnumber");
                        String currentstate = server.getString("currentstate");
                        String status = server.getString("status");
                        String location = server.getString("location");
                        String ipaddress = server.getString("ipaddress");
                        String starttime = server.getString("starttime");

                        Button button = new Button(BahubaliActivity.this);
                        button.setText(serialnumber + "  " + location + "  " + currentstate + "  " + status);
                        if (status.equals("FAILED")) {
                            button.setBackgroundResource(R.drawable.button_failed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("PASSED")) {
                            button.setBackgroundResource(R.drawable.button_passed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("TESTING")) {
                            button.setBackgroundResource(R.drawable.button_testing);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("OFFLINE")) {
                            button.setBackgroundResource(R.drawable.button_offline);
                            button.setTextColor(Color.WHITE);
                        } else {
                            button.setBackgroundResource(R.drawable.button_other);
                            button.setTextColor(Color.WHITE);
                        }

                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyDialogStyle);
                                builder.setMessage("\nS/N: " + serialnumber + "\nLocation: " + location + "\nStage: " + currentstate
                                        + "\nStatus: " + status + "\nIP: " + ipaddress + "\nStart Time: " + starttime);
                                builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                if (status.equals("FAILED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_failed);
                                    dialog.show();
                                } else if (status.equals("TESTING")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_testing);
                                    dialog.show();
                                } else if (status.equals("OFFLINE")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_server_offline);
                                    dialog.show();
                                } else if (status.equals("PASSED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_passed);
                                    dialog.show();
                                } else {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_other);
                                    dialog.show();
                                }
                            }
                        });
                        linearLayout_debug.addView(button);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class GetDataBurnin extends AsyncTask<Void, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(Void... voids) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("Working...");
            try {
                String url = "https://raw.githubusercontent.com/v-dmytriiev/data/main/data.json";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("BAHUBALI BURN-IN TEST");
            LinearLayout linearLayout_debug = findViewById(R.id.linearLayout1_bahubali);
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray servers = json.getJSONArray("data");


                    linearLayout_debug.removeAllViews();

                    for (int i = 0; i < servers.length(); i++) {
                        JSONObject server = servers.getJSONObject(i);
                        String serialnumber = server.getString("serialnumber");
                        String currentstate = server.getString("currentstate");
                        String status = server.getString("status");
                        String location = server.getString("location");
                        String ipaddress = server.getString("ipaddress");
                        String starttime = server.getString("starttime");

                        Button button = new Button(BahubaliActivity.this);
                        button.setText(serialnumber + "  " + location + "  " + currentstate + "  " + status);
                        if (status.equals("FAILED")) {
                            button.setBackgroundResource(R.drawable.button_failed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("PASSED")) {
                            button.setBackgroundResource(R.drawable.button_passed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("TESTING")) {
                            button.setBackgroundResource(R.drawable.button_testing);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("OFFLINE")) {
                            button.setBackgroundResource(R.drawable.button_offline);
                            button.setTextColor(Color.WHITE);
                        } else {
                            button.setBackgroundResource(R.drawable.button_other);
                            button.setTextColor(Color.WHITE);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyDialogStyle);
                                builder.setMessage("\nS/N: " + serialnumber + "\nLocation: " + location + "\nStage: " + currentstate
                                        + "\nStatus: " + status + "\nIP: " + ipaddress + "\nStart Time: " + starttime);
                                builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                if (status.equals("FAILED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_failed);
                                    dialog.show();
                                } else if (status.equals("TESTING")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_testing);
                                    dialog.show();
                                } else if (status.equals("OFFLINE")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_server_offline);
                                    dialog.show();
                                } else if (status.equals("PASSED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_passed);
                                    dialog.show();
                                } else {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_other);
                                    dialog.show();
                                }
                            }
                        });
                        linearLayout_debug.addView(button);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class GetDataL123 extends AsyncTask<Void, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(Void... voids) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("Working...");
            try {
                String url = "https://raw.githubusercontent.com/v-dmytriiev/data/main/data.json";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            TextView myTextView = findViewById(R.id.user_test2_bahubali);
            myTextView.setText("BAHUBALI L12-3 TEST");
            LinearLayout linearLayout_debug = findViewById(R.id.linearLayout1_bahubali);
            if (result != null) {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray servers = json.getJSONArray("data");


                    linearLayout_debug.removeAllViews();

                    for (int i = 0; i < servers.length(); i++) {
                        JSONObject server = servers.getJSONObject(i);
                        String serialnumber = server.getString("serialnumber");
                        String currentstate = server.getString("currentstate");
                        String status = server.getString("status");
                        String location = server.getString("location");
                        String ipaddress = server.getString("ipaddress");
                        String starttime = server.getString("starttime");

                        Button button = new Button(BahubaliActivity.this);
                        button.setText(serialnumber + "  " + location + "  " + currentstate + "  " + status);
                        if (status.equals("FAILED")) {
                            button.setBackgroundResource(R.drawable.button_failed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("PASSED")) {
                            button.setBackgroundResource(R.drawable.button_passed);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("TESTING")) {
                            button.setBackgroundResource(R.drawable.button_testing);
                            button.setTextColor(Color.WHITE);
                        } else if (status.equals("OFFLINE")) {
                            button.setBackgroundResource(R.drawable.button_offline);
                            button.setTextColor(Color.WHITE);
                        } else {
                            button.setBackgroundResource(R.drawable.button_other);
                            button.setTextColor(Color.WHITE);
                        }
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext(), R.style.MyDialogStyle);
                                builder.setMessage("\nS/N: " + serialnumber + "\nLocation: " + location + "\nStage: " + currentstate
                                        + "\nStatus: " + status + "\nIP: " + ipaddress + "\nStart Time: " + starttime);
                                builder.setPositiveButton("close", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                    }
                                });
                                if (status.equals("FAILED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_failed);
                                    dialog.show();
                                } else if (status.equals("TESTING")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_testing);
                                    dialog.show();
                                } else if (status.equals("OFFLINE")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_server_offline);
                                    dialog.show();
                                } else if (status.equals("PASSED")) {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_passed);
                                    dialog.show();
                                } else {
                                    AlertDialog dialog = builder.create();
                                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.info_servers_other);
                                    dialog.show();
                                }
                            }
                        });
                        linearLayout_debug.addView(button);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
            } else {
                String scanResult = result.getContents();
                Toast.makeText(this, "Scanned: " + scanResult, Toast.LENGTH_SHORT).show();
                SearchView mySearch = findViewById(R.id.search_server_bahubali);
                mySearch.setQuery(scanResult, true);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String scannedText = getIntent().getStringExtra("scan");
        if (scannedText != null && !scannedText.isEmpty()) {
            SearchView mySearch = findViewById(R.id.search_server_bahubali);
            mySearch.setQuery(scannedText, true);
        }
    }

}


