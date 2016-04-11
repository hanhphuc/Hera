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
import android.widget.EditText;
import android.widget.TextView;



public class BMI_activity extends Activity {
    EditText txtBMI;
    TextView txtnoidung;
    public static Float BMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_activity);
        // lấy ActionBar
        ActionBar actionBar = getActionBar();
       // hiển thị nút Up ở Home icon
       actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));

        txtBMI = (EditText)findViewById(R.id.bmi);
        txtnoidung = (TextView)findViewById(R.id.noidung);
        //lấy intent gọi Activity này
        Intent callerIntent=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle packageFromCaller=
                callerIntent.getBundleExtra("MyPackage");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        float a=packageFromCaller.getFloat("cao");
        float b=packageFromCaller.getFloat("nang");
        float c=packageFromCaller.getFloat("nangmongmuon");
        //tiến hành xử lý
        BMI_Function(a,b,c);
    }
    public  void BMI_Function(float cao, float nang, float nangmongmuon){
         BMI = nang*10000/(cao*cao);
        float BMI_2 = nangmongmuon*10000/(cao*cao);
        String kq = "";
        String khuyen = "";
        if(BMI<16) {
            kq = "Skiny level III";
            if (BMI_2 < BMI) {
                khuyen = "You should not lose your weight to save the health";
            } else {
                khuyen = "You should gain your weight to save the health";
            }
        }
        if (BMI >= 16 && BMI<17) {
            kq = "Skiny level II";
            if (BMI_2 < BMI) {
                khuyen = "You should not lose your weight to save the health";
            } else {
                khuyen = "You should gain your weight to save the health";
            }

        }
        if (BMI >=17 && BMI<18.5) {
            kq = "Skiny level I";
            if (BMI_2 < BMI) {
                khuyen = "You should not lose your weight to save the health";
            } else {
                khuyen = "You should gain your weight to save the health";
            }

        }
        if (BMI >=18.5 && BMI<25) {
            kq = "Normal ";
            if (BMI_2 < BMI) {
                khuyen = "You should not lose your weight to save the health";
            } else {
                khuyen = "You should maintain a healthy weight to save the health";
            }

        }
        if (BMI >=25 && BMI<30) {
            kq = "Overweight ";
            if (BMI_2 <18.5) {
                khuyen = "You should lose weight but you need attention for your desired weight because it is at a skinny";
            } else if(BMI_2<25){
                khuyen = "You should plan meals and exercise to achieve this weight";
            }
            else{
                khuyen = "You should not gain weight again, it can make you obese";
            }


        }
        if (BMI >=30 && BMI<35) {
            kq = "Obesity level I ";
            if (BMI_2 <18.5) {
                khuyen = "You should lose weight but you need attention for your desired weight because it is at a skinny";
            } else if(BMI_2<25){
                khuyen = "You should plan meals and exercise to achieve this weight";
            }
            else{
                khuyen = "You should not gain weight again, it can make you obese";
            }


        }
        if (BMI >=35 && BMI<40) {
            kq = "Obesity level II ";
            if (BMI_2 <18.5) {
                khuyen = "You should lose weight but you need attention for your desired weight because it is at a skinny";
            } else if(BMI_2<25){
                khuyen = "You should plan meals and exercise to achieve this weight";
            }
            else{
                khuyen = "You should not gain weight again, it can make you obese";
            }


        }

        if (BMI >40) {
            kq = "Béo phì độ III ";
            if (BMI_2 <18.5) {
                khuyen = "You should lose weight but you need attention for your desired weight because it is at a skinny";
            } else if(BMI_2<25){
                khuyen = "You should plan meals and exercise to achieve this weight";
            }
            else{
                khuyen = "You should not gain weight again, it can make you obese";
            }

        }

        txtBMI.setText(BMI+"");
        txtnoidung.setText(kq +"\n"+ khuyen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi_activity, menu);
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

    public void btnNext(View view) {
        if(view.getId() == R.id.buttonNext){
            Intent mhmain = new Intent(BMI_activity.this,MainActivity.class);
            startActivity(mhmain);
        }
    }
}
