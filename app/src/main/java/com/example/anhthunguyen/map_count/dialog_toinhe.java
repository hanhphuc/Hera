package com.example.anhthunguyen.map_count;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class dialog_toinhe extends Activity {
    private Spinner spinnermonan,spinnerunit;
    EditText editTextCalo;
    Button btnback,btnadd;
    private List<ParseObject> ob;
    private ProgressDialog mProgressDialog;
    private ArrayList<String> arrmonan;
    private ArrayAdapter<String> adapterfood;
    private int i,calo;
    private int unit = 1;
    private String luongCalo,tenmonan;
    public static int Calotoinhe=0;
    private Bundle bundle = new Bundle();
    private Bundle bundle1 = new Bundle();
    private String arrunit[] ={
            "1", "2", "3", "4", "5", "6"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_sang);
        ///lấy ActionBar
        android.app.ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));

        spinnermonan = (Spinner) findViewById(R.id.spnPin);
        spinnerunit = (Spinner) findViewById(R.id.spnPinUnit);
        editTextCalo = (EditText) findViewById(R.id.edtCalo);
        btnback = (Button) findViewById(R.id.btnBack);
        btnadd = (Button) findViewById(R.id.btnAdd);
        ArrayAdapter<String> adapterunit = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrunit
                );
        adapterunit.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinnerunit.setAdapter(adapterunit);


        //lay i
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle bd= callerIntent.getBundleExtra("Mycount_i");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        i = bd.getInt("i");

        arrmonan = new ArrayList<>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Food");
        query.whereEqualTo("Mathucan", i);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> hangDT, com.parse.ParseException e) {
                if (e == null) {
                    for (int i = 0; i < hangDT.size(); i++) {
                        ParseObject hang = hangDT.get(i);
                        //  ParseFile file = hang.getParseFile("LogoHangSX");
                        //  byte[] fileLogo = file.getData();
                        arrmonan.add(
                                hang.getString("Tenthucan")

                        );
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>
                            (
                                    dialog_toinhe.this,
                                    android.R.layout.simple_spinner_item,
                                    arrmonan
                            );

                    spinnermonan.setAdapter(adapter);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        spinnerunit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = Integer.parseInt(arrunit[position]);
                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Food");
                query2.whereEqualTo("Tenthucan", tenmonan);
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> hangDT, com.parse.ParseException e) {
                        if (e == null) {
                            for (int i = 0; i < hangDT.size(); i++) {
                                ParseObject hang = hangDT.get(i);

                                //  ParseFile file = hang.getParseFile("LogoHangSX");
                                //  byte[] fileLogo = file.getData();
//                        arrmonan.add(
//                                hang.getString("Tenthucan")
                                calo = Integer.parseInt(hang.getString("Calothucan"));
                                Toast.makeText(dialog_toinhe.this, calo + "", Toast.LENGTH_LONG).show();
                                Calotoinhe = calo*unit;
                                editTextCalo.setText(String.valueOf(calo * unit)


                                );
                            }

                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                unit = 0;
            }
        });


        spinnermonan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenmonan = String.valueOf(arrmonan.get(position));
                Toast.makeText(dialog_toinhe.this, tenmonan + "", Toast.LENGTH_SHORT).show();

                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Food");
                query2.whereEqualTo("Tenthucan", tenmonan);
                query2.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> hangDT, com.parse.ParseException e) {
                        if (e == null) {
                            for (int i = 0; i < hangDT.size(); i++) {
                                ParseObject hang = hangDT.get(i);

                                //  ParseFile file = hang.getParseFile("LogoHangSX");
                                //  byte[] fileLogo = file.getData();
//                        arrmonan.add(
//                                hang.getString("Tenthucan")
                                calo = Integer.parseInt(hang.getString("Calothucan"));
                                Toast.makeText(dialog_toinhe.this, calo + "", Toast.LENGTH_LONG).show();
                                Calotoinhe = calo*unit;
                                editTextCalo.setText(String.valueOf(calo * unit)


                                );
                            }

                        } else {
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tenmonan = "";
            }


        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhfoodtong = new Intent(dialog_toinhe.this, food_activity.class);
                bundle1.putInt("ii", calo*unit);
                mhfoodtong.putExtra("Mycount_calosang", bundle1);
                startActivity(mhfoodtong);

            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhfoodtong = new Intent(dialog_toinhe.this, food_activity.class);
                startActivity(mhfoodtong);
            }
        });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dialog_sang, menu);
        return true;
    }

}
