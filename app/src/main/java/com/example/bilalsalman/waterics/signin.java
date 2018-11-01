package com.example.bilalsalman.waterics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class signin extends AppCompatActivity {
    //private DatabaseReference userdata;

    TextView phone;
    Spinner spin;
    FirebaseAuth mAuth;
    String mVerificationId,num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
        phone = (TextView)findViewById(R.id.phone_signin);
        spin = (Spinner)findViewById(R.id.country_code);

        //userdata = FirebaseDatabase.getInstance().getReference("Users");

        String [] country_names = {"Pakistan","India","Afghanistan","Bangladesh"};


        spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,country_names));


        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                num = phone.getText().toString().trim();

                if (num.equals("")) {
                    phone.setError("Enter phone number in format: +923xxxxxxxxx!");
                    phone.requestFocus();
                    return;
                }

                String c_code = "";
                String cc_name = (String) spin.getSelectedItem();
                if(cc_name == "Pakistan")
                    c_code = "+92";
                if(cc_name == "India")
                    c_code = "+92";
                if(cc_name == "Afghanistan")
                    c_code = "+92";
                if(cc_name == "Bangladesh")
                    c_code = "+92";
                Intent intent = new Intent(getApplicationContext(),tabbed_activity.class);
               // Intent intent = new Intent(signin.this,SMSCode_in.class);
                intent.putExtra("num",num);
                intent.putExtra("c_code",c_code);
                startActivity(intent);


                //String id = userdata.push().getKey();
               // UserDataForFireBase obj = new UserDataForFireBase("asd","123","asas","ahmed",id);
               // userdata.child(id).setValue(obj);
                //userdata.setValue("hello world");
                //Toast.makeText(getApplicationContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void registernow(View view)
    {
        Intent intent = new Intent(signin.this, MainActivity.class);
        startActivity(intent);
    }

    /*@Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            //if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com"))
            //    startActivity(new Intent(this, navigation.class));
            //else
            Intent intent = new Intent(this, tabbed_activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }*/

}

