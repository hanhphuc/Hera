package com.example.anhthunguyen.map_count;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity{
    ImageView imvprofile,imvrun,imvfood,imvnews,imvproduct,imvcheck,imvsetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

   //     Parse.enableLocalDatastore(this);


        // lấy ActionBar
        ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));

//        Parse.initialize(this);

         imvprofile = (ImageView)findViewById(R.id.imvprofile);
       imvprofile.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Intent profile_info = new Intent(MainActivity.this, profile_ifo.class);
               startActivity(profile_info);

            }
       });
        imvrun = (ImageView)findViewById(R.id.imvrun);
        imvrun.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Intent run = new Intent(MainActivity.this,RunActivity.class);
                startActivity(run);

            }
        });
        imvfood = (ImageView)findViewById(R.id.imvfood);
       imvfood.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                Intent food = new Intent(MainActivity.this,food_activity.class);
                startActivity(food);

            }
       });
        imvnews = (ImageView)findViewById(R.id.imvnewss);
        imvnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news = new Intent(MainActivity.this,newsActivity.class);
                startActivity(news);

            }
        }); // calling onClick() method
        imvproduct = (ImageView)findViewById(R.id.imvproduct);
        imvproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("http://giamcan24h.edu.vn/thuoc-giam-can");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);

            }
        });
        imvcheck = (ImageView)findViewById(R.id.imvtick);
        imvcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent check = new Intent(MainActivity.this,profile.class);
                startActivity(check);

            }
        });
        imvsetting = (ImageView)findViewById(R.id.imvsetting);
        imvsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setting = new Intent(MainActivity.this,AlarmActivity.class);
                startActivity(setting);

            }
        });


   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

   /* public void OnclickIntent(View view) {
        switch (view.getId()){
            case R.id.imvprofile:
                Intent profile_info = new Intent(MainActivity.this,profile_ifo.class);
                startActivity(profile_info);
                break;
            case R.id.imvfood:
                Intent food = new Intent(MainActivity.this,food_activity.class);
                startActivity(food);
                break;
            case R.id.imvnewss:
                Intent news = new Intent(MainActivity.this,News_Activity.class);
                startActivity(news);
                break;
            case R.id.imvproduct:
                Uri webpage = Uri.parse("http://giamcan24h.edu.vn/thuoc-giam-can");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
                break;
            case R.id.imvtick:
                Intent check = new Intent(MainActivity.this,BMI_activity.class);
                startActivity(check);
                break;
            case R.id.imvsetting:
                Intent setting = new Intent(MainActivity.this,setting_Activity.class);
                startActivity(setting);
                break;
        }
    }*/
}
