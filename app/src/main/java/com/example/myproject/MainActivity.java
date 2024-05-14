package com.example.myproject;


import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myproject.R;
import com.example.myproject.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button sign;
    TextView register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        sign=findViewById(R.id.SingUp);
        register=findViewById(R.id.Register);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail=email.getText().toString();
                String pass=password.getText().toString();

                SignIn(mail, pass);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),Register.class);
                startActivity(i2);
            }
        });


    }

    private void SignIn(String mail, String pass) {
        mAuth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Connexion réussie, mise à jour de l'interface utilisateur avec les informations de l'utilisateur connecté
                            Log.d("MainActivity", "signInWithEmail:success");
                            Toast.makeText(MainActivity.this, "Connexion réussie.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Quiz1.class));
                            overridePendingTransition(R.anim.exit,R.anim.entry);
                            finish();
                        } else {
                            // Échec de la connexion, affichez un message à l'utilisateur
                            Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Échec de la connexion.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


