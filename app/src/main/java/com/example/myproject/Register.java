package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email,password,password1;

    Button bregister;

    FirebaseAuth MyAuthentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(getApplicationContext());
        setContentView(R.layout.activity_register);
        MyAuthentification=FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password1=findViewById(R.id.password1);
        password1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        bregister=findViewById(R.id.bregister);
        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String pass=password.getText().toString();
                String pass1=password1.getText().toString();
                if(TextUtils.isEmpty(mail)|| TextUtils.isEmpty(pass)|| TextUtils.isEmpty(pass1)){
                    Toast.makeText(Register.this,"Veuiller remplir les champs obligatoires !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pass.length()<6){
                    Toast.makeText(Register.this,"Nombre du caractères insuffisant",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pass.equals(pass1)){
                    Toast.makeText(Register.this,"Vérification invalide",Toast.LENGTH_SHORT).show();
                    return;
                }

                SignUp(mail,pass);
            }
        });

    }

    public void SignUp(String email,String pass){
        MyAuthentification.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"Enregistrement effectué",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            overridePendingTransition(R.anim.exit,R.anim.entry);
                            finish();
                        }
                        else{
                            Toast.makeText(Register.this, "Enregistrement échoué", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}