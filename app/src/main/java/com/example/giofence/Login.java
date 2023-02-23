package com.example.giofence;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Login extends AppCompatActivity {

    EditText email,password;
    TextView login;
    TextView signup,text,text2,add;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        text =findViewById(R.id.text);
        text.setVisibility(View.INVISIBLE);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.link);
        text2=findViewById(R.id.text2);
        text2.setVisibility(View.INVISIBLE);
        add = findViewById(R.id.Add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Add_Child.class));
            }
        });


        auth = FirebaseAuth.getInstance();

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if(user !=null){
//            startActivity(new Intent(Login.this,MapsActivity.class));
//        }






        signup.setOnClickListener(v -> startActivity(new Intent(Login.this,Register.class)));

        login.setOnClickListener(v -> {


            text.setVisibility(View.VISIBLE);

            text2.setVisibility(View.VISIBLE);

            ProgressDialog pd = new ProgressDialog(Login.this);


            String str_email = email.getText().toString();
            String str_password = password.getText().toString();

            if (TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)) {
                Toast.makeText(Login.this, "All fields are required!!", Toast.LENGTH_SHORT).show();
            } else {
                auth.signInWithEmailAndPassword(str_email, str_password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());

                                    reference.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            Intent intent = new Intent(Login.this, MapsActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            pd.setMessage("authentication failed!!");
                                            pd.dismiss();
                                        }
                                    });
                                } else {
                                    pd.dismiss();
                                    Toast.makeText(Login.this, "Authentication failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });





    }

}