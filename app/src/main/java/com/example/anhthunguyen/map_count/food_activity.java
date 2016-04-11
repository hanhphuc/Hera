package com.example.anhthunguyen.map_count;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class food_activity extends Activity  {
    Button date;
    int year_x,month_x,day_x;
    static final int DILOG_ID=0;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();

    private int ii;
    public static int CaloTong;

    private ImageView btn1,btn2,btn3,btn4,btn5,btn6;
    private Bundle bundle = new Bundle();
    private Bundle bd = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_activity);

        // lấy ActionBar
        ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00BFA5")));

        date = (Button) findViewById(R.id.date);
        //progressbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        textView = (TextView) findViewById(R.id.textView1);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 2500) {
                    progressStatus = CaloTong*100/(2500);
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        //lấy thời gian hiện tại
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH)+1;
        day_x = cal.get(Calendar.DAY_OF_MONTH);

        date.setText(year_x + " / " + month_x + " / " + day_x);
        // gọi phương thức
        showDialogButtonClick();




        btn1 = (ImageView)findViewById(R.id.sang);
        btn2 = (ImageView)findViewById(R.id.trua);
        btn3 = (ImageView)findViewById(R.id.toi);
        btn4 = (ImageView)findViewById(R.id.sangnhe);
        btn5 = (ImageView)findViewById(R.id.truanhe);
        btn6 = (ImageView)findViewById(R.id.toinhe);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);

                bundle.putInt("i", 1);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this,dialog_trua.class);

                bundle.putInt("i", 2);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);

                bundle.putInt("i", 3);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);

                bundle.putInt("i", 4);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);

                bundle.putInt("i", 5);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mhdalog = new Intent(food_activity.this, dialog_sang.class);

                bundle.putInt("i", 6);
                mhdalog.putExtra("Mycount_i", bundle);
                startActivity(mhdalog);
            }
        });

        //lay i
        Intent caller=this.getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        bd= caller.getBundleExtra("Mycount_calosang");
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
       // ii = bd.getInt("ii");

        CaloTong = dialog_sang.Calosang+dialog_trua.Calotrua+
                dialog_toi.Calotoi+dialog_sangnhe.Calosangnhe+
                dialog_truanhe.Calotruanhe+dialog_toinhe.Calotoinhe;
        Toast.makeText(food_activity.this,CaloTong+"",Toast.LENGTH_LONG).show();


    }



    /**
     * Hàm hiển thị DatePicker dialog
     */
    public void showDialogButtonClick(){
        date = (Button)findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DILOG_ID);
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id==DILOG_ID)
            return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
        return null;
    }
    private DatePickerDialog.OnDateSetListener dpickerListener=new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year_x=year;
                    month_x=monthOfYear+1;
                    day_x=dayOfMonth;
                    date.setText(year_x+" / "+month_x+" / "+day_x);
                }
            };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_activity, menu);
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


//    public void onDialog(View view) {
//        if(view.getId()==R.id.sang){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 1);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//        if(view.getId()==R.id.trua){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 2);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//        if(view.getId()==R.id.toi){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 3);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//        if(view.getId()==R.id.sangnhe){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 4);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//        if(view.getId()==R.id.truanhe){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 5);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//        if(view.getId()==R.id.toinhe){
//            Intent mhdalog = new Intent(food_activity.this,dialog_sang.class);
//            bundle.putInt("i", 6);
//            mhdalog.putExtra("Mycount_i", bundle);
//            startActivity(mhdalog);
//        }
//

 //   }

}