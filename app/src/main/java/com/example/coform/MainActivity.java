package com.example.coform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button Savebtn;
    private EditText editText;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirestore=FirebaseFirestore.getInstance();
        Savebtn=(Button) findViewById(R.id.button1);
        editText=(EditText) findViewById(R.id.edittext1);


        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=editText.getText().toString();
                Map<String,String> usermap=new HashMap<>();
                usermap.put("name",username);

                mFirestore.collection("aaaa").add(usermap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Added to firebase", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Something wenr wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
