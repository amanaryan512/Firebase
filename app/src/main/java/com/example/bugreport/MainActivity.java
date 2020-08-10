package com.example.bugreport;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    EditText  msgdata;
    Button send, details;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        msgdata = findViewById(R.id.msgData);
        send = (Button)findViewById(R.id.btn_send);
        details = (Button)findViewById(R.id.btn_details);
        Firebase.setAndroidContext(this);


;
        String UniqueID;
        UniqueID = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        firebase=new Firebase("https://amanfirstfirebase.firebaseio.com/users"+UniqueID);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                details.setEnabled(true);


                final String msg = msgdata.getText().toString();

                Firebase child_msg = firebase.child("Message");
                child_msg.setValue(msg);
                if (msg.isEmpty()) {
                    msgdata.setError("this is an required field");
                    send.setEnabled(false);
                } else {
                    msgdata.setError(null);
                    send.setEnabled(true);
                }
                Toast.makeText(MainActivity.this, "Your data is sended to server", Toast.LENGTH_SHORT).show();
                details.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("sended Details :")
                                .setMessage(" Message -" + msg).show();

                    }
                });
            }
        });
            }
        }
