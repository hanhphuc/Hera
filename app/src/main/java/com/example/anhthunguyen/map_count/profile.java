package com.example.anhthunguyen.map_count;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;


public class profile extends Activity {

    //Tạo một mảng dữ liệu giả
    String arr[]={
            "High Activity",
            "Medium Activity",
            "Light Activity"};
    //TextView selection;
    EditText txtcao,txtnang,txtnangmuongmuon,txttuoi,txthoatdong;
    RadioGroup txtgioitinh;
    Button btn;
    public static String scao;
    public static String snang;
    public static String shoatdong;
    public static String sgioitinh;
    public static String stuoi;
    public static Integer gioitinh;
    public static Float cao,nang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // lấy ActionBar
       ActionBar actionBar = getActionBar();
//        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));

        txtcao = (EditText)findViewById(R.id.chieucao);
        txtnang = (EditText)findViewById(R.id.cannang);
        txtgioitinh=(RadioGroup)findViewById(R.id.radiogroup);
        txttuoi=(EditText)findViewById(R.id.tuoi);
        txtnangmuongmuon =(EditText)findViewById(R.id.cannangmongmuon);
        gioitinh=txtgioitinh.getCheckedRadioButtonId();
        switch (gioitinh)
        {
            case R.id.radionam:
                sgioitinh="Male";
                gioitinh=1;
                break;
            case R.id.radionu:
                sgioitinh="Female";
                gioitinh=0;
                break;
        }

        btn = (Button)findViewById(R.id.btnkq);
        //Lấy đối tượng Spinner ra
        Spinner spin=(Spinner) findViewById(R.id.spinner);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());
        //sự kiện onclick
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(profile.this, BMI_activity.class);
                Bundle bundle = new Bundle();
                 cao = Float.parseFloat(txtcao.getText().toString());
                 nang = Float.parseFloat(txtnang.getText().toString());
                float nangmongmuon = Float.parseFloat(txtnangmuongmuon.getText().toString());
                //đưa dữ liệu riêng lẻ vào Bundle
                bundle.putFloat("cao", cao);
                bundle.putFloat("nang", nang);
                bundle.putFloat("nangmongmuon",nangmongmuon);
                //Đưa Bundle vào Intent

                stuoi=txttuoi.getText().toString();
                scao=txtcao.getText().toString();
                snang=txtnang.getText().toString();

                myIntent.putExtra("MyPackage", bundle);
                startActivity(myIntent);
            }
        });

    }
    ///Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener
    {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
           shoatdong=(arr[arg2].toString());
        }
        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {
            shoatdong="";
        }
    }




        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
