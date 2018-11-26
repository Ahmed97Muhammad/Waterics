package com.example.bilalsalman.waterics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class signin extends AppCompatActivity {
    private DatabaseReference userdata;

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

        userdata = FirebaseDatabase.getInstance().getReference();


        final ArrayList<UserDataForFireBase> contacts = new ArrayList<>();


        userdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

  //              Log.d("Fuckme","hello");

                DataSnapshot contactSnapshot = dataSnapshot.child("Users");
                Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();

                for (DataSnapshot contact : contactChildren) {
                    UserDataForFireBase c = contact.getValue(UserDataForFireBase.class);
//                    Log.d("Fuckme",c.getNum());
                    contacts.add(c);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        String [] country_names = {"Pakistan","India","Afghanistan","Bangladesh"};


        spin.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,country_names));

        Button signin = (Button)findViewById(R.id.signin);
        signin.setHapticFeedbackEnabled(true);
        findViewById(R.id.signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num = phone.getText().toString().trim();
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                if (num.equals("")) {
                    phone.setError("Enter phone number in format: 03xxxxxxxx!");
                    phone.requestFocus();
                    return;
                }

                boolean register_user = false;


                for(int i=0;i<contacts.size();i++)
                {
                    if(contacts.get(i).getNum().equals("+92" + num))
                    {
                       // Log.d("Fuck",num);
                        register_user = true;
                    }
                }

                if(!register_user)
                {
                    Toast.makeText(getApplicationContext(), "Phone Number Not Registered!! Please Register first. Click 'Register Now' to register yourself", Toast.LENGTH_SHORT).show();
                    return;
                }

                String c_code = "";
                String cc_name = (String) spin.getSelectedItem();

                if(cc_name == "Pakistan")
                    c_code = "+92";
                if(cc_name == "India")
                    c_code = "+91";
                if(cc_name == "Afghanistan")
                    c_code = "+93";
                if(cc_name == "Bangladesh")
                    c_code = "+880";

                //Intent intent = new Intent(getApplicationContext(),tabbed_activity.class);

                Intent intent = new Intent(signin.this,SMSCode_in.class);
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

        Button register = (Button)findViewById(R.id.register);
        register.setHapticFeedbackEnabled(true);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                Intent intent = new Intent(signin.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }



  /*  @Override
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
    }
*/


    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            if(mAuth.getCurrentUser().getPhoneNumber().equals("+923218710363"))
                startActivity(new Intent(this, admin_tabbed.class));
            else
                startActivity(new Intent(this, tabbed_activity.class));
        }
    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
}

