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
import android.widget.Toast;


public class Login extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // lấy ActionBar
        ActionBar actionBar = getActionBar();
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));

    }
    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.btnLinkToLoginScreen)
        {
            EditText a = (EditText)findViewById(R.id.registerName);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.registerPassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Intent i = new Intent(Login.this, MainActivity.class);
                i.putExtra("Username",str);
                startActivity(i);
            }
            else
            {
                Toast temp = Toast.makeText(Login.this , "Username and password don't match!" , Toast.LENGTH_SHORT);
                temp.show();
            }



        }
        if(v.getId() == R.id.btnRegister)
        {
            Intent i = new Intent(Login.this, SignUp.class);
            startActivity(i);

        }

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
            Toast.makeText(this,"you choose MenuItem: "+item.getTitle(),Toast.LENGTH_LONG).show();
        }else if (id==R.id.action_exit){
            Toast.makeText(this,"You choose MenuItem; "+item.getTitle()+"\nClosing App !",Toast.LENGTH_LONG).show();
         return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
