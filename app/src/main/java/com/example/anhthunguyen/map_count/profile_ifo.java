package com.example.anhthunguyen.map_count;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class profile_ifo extends Activity {
    //SignUp signUp = new SignUp();
    //BMI_activity bmi_activity = new BMI_activity();
    TextView ten,tuoi,gioitinh,cao, nang,calo;
    Double luongcalo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ifo);
        // lấy ActionBar
        ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));
        // lấy địa chỉ của các textview
        ten=(TextView)findViewById(R.id.ten);
        tuoi=(TextView)findViewById(R.id.tuoi);
        gioitinh=(TextView)findViewById(R.id.gioitinh);
        cao=(TextView)findViewById(R.id.chieucao);
        calo=(TextView)findViewById(R.id.calo);
        nang=(TextView)findViewById(R.id.cannang);
       // bmi=(TextView)findViewById(R.id.bmi);

        // gửi dữ liệu
        ten.setText(SignUp.namestr);
        tuoi.setText(profile.stuoi);
        gioitinh.setText(profile.sgioitinh);
        cao.setText(profile.scao);
        nang.setText(profile.snang);
       // bmi.setText(BMI_activity.BMI.toString());

        int sotuoi = Integer.parseInt(profile.stuoi);
        if(profile.gioitinh==1)
            luongcalo = (13.397*profile.nang+4.799*profile.cao-5.677*sotuoi + 88.362);
        else
            luongcalo = (9.247*profile.nang+4.799*profile.cao-4.330*sotuoi+ 447.593 );

        String strcalo = String.format("%.02f", luongcalo);
        calo.setText(strcalo);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile_ifo, menu);
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
