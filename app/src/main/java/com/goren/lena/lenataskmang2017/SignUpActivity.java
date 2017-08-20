package com.goren.lena.lenataskmang2017;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.goren.lena.lenataskmang2017.data.DBUtils;
import com.goren.lena.lenataskmang2017.data.MyUser;

public class SignUpActivity extends AppCompatActivity {
    private EditText etEmail, etPassword, etRePassword, etName, etPhone;
    private Button btnSave;

    //1


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);
        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSave=(Button)findViewById(R.id.btnSave);

        //2

        eventHandler();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    private void eventHandler() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datahandler();
            }
        });
    }

    private void datahandler() {
         final String stEmail = etEmail.getText().toString();
        final String stPassword = etPassword.getText().toString();
        final String stRePassword = etRePassword.getText().toString();
        final String stName = etName.getText().toString();
        final String stPhone = etPhone.getText().toString();

        boolean isOk = true;
        if (stEmail.length() < 6 || stEmail.indexOf('@') < 1)
        {
            etEmail.setError("Wrong Email");
            isOk = false;
        }
        //TODO complete the rest check...

        if (isOk)
        {
            final ProgressDialog progressDialog = ProgressDialog.show(this, "Wait", "Signing up", true, true);
            progressDialog.show();
            DBUtils.auth.createUserWithEmailAndPassword(stEmail, stPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {

                    if (task.isSuccessful())
                    {
                        MyUser myUser=new MyUser();
                        myUser.setName(stName);
                        myUser.setPhone(stPhone);
                        myUser.setuKey_email(stEmail);

                        DBUtils.myUsersRef.child(myUser.getuKey_email()).setValue(myUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful())
                                {
                                    finish();
                                }
                            }
                        });


                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        task.getException().printStackTrace();
                    }
                }
            });

        }
        else
        {

        }
    }


}
