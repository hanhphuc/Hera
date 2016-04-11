package com.example.anhthunguyen.map_count;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by priya on 3/10/2015.
 */
public class SignUp extends Activity {
    public static String namestr,emailstr, unamestr;

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        // lấy ActionBar
        ActionBar actionBar = getActionBar();
        // hiển thị nút Up ở Home icon
        actionBar.setDisplayHomeAsUpEnabled(true);
        // set màu cho actionBar
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f28b0c")));
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.Bsignupbutton)
        {
            EditText name = (EditText)findViewById(R.id.TFname);
            EditText email = (EditText)findViewById(R.id.TFemail);
            EditText uname = (EditText)findViewById(R.id.TFuname);
            EditText pass1 = (EditText)findViewById(R.id.TFpass1);
            EditText pass2 = (EditText)findViewById(R.id.TFpass2);

             namestr = name.getText().toString();
             emailstr = email.getText().toString();
             unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            if(namestr.equals("")) {
                Toast.makeText(SignUp.this, "You have to enter your name!", Toast.LENGTH_SHORT).show();
            }
            else if(emailstr.equals(""))
            {
                Toast.makeText(SignUp.this, "You have to enter your email!", Toast.LENGTH_SHORT).show();

            }
            else if(unamestr.equals("")){
                Toast.makeText(SignUp.this, "You have to enter your username!", Toast.LENGTH_SHORT).show();

            }
            else if(pass1str.equals("")){
                Toast.makeText(SignUp.this, "You have to enter your password!", Toast.LENGTH_SHORT).show();

            }
            else if(pass2str.equals("")){
                Toast.makeText(SignUp.this, "You have to enter your password confirm !", Toast.LENGTH_SHORT).show();
            }
            else if (!pass1str.equals(pass2str)){
                Toast pass = Toast.makeText(SignUp.this, "Passwords don't match!", Toast.LENGTH_SHORT);
                pass.show();
            }
            else{
                //insert the detailes in database
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);

                helper.insertContact(c);
                Toast.makeText(SignUp.this,"Successful",Toast.LENGTH_LONG).show();
                Intent mhprofile = new Intent(SignUp.this,profile.class);
                startActivity(mhprofile);ParseObject testObject = new ParseObject("User");
                int i;
                i = helper.countid();
                testObject.put("id_user", i);
                testObject.put("name", namestr);
                testObject.put("email", emailstr);
                testObject.put("username", unamestr);
                testObject.put("pass", pass1str);
                testObject.saveInBackground();
            }


            }

    }


}
