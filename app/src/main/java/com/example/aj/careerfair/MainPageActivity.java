package com.example.aj.careerfair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPageActivity extends AppCompatActivity {

    private Button buttonLogout;
    private TextView textViewEmail;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null)
        {
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();

        textViewEmail=(TextView)findViewById(R.id.textviewEmail);
        textViewEmail.setText("Welcome "+firebaseUser.getEmail());

        buttonLogout=(Button)findViewById(R.id.buttonlogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(MainPageActivity.this,LoginActivity.class));
                Toast.makeText(MainPageActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
