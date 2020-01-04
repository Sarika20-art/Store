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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        editTextName = (EditText) findViewById(R.id.edittext1);
        findViewById(R.id.button1).setOnClickListener((View.OnClickListener) this);
    }
    private boolean validateInputs(String name){
        if (name.isEmpty())
        {
            editTextName.setError("Name Required");
            editTextName.requestFocus();
            return true;
        }
        return false;
    }
    public void onClick(View view)
    {
        String name=editTextName.getText().toString().trim();
        if (!validateInputs(name))
        {
            CollectionReference dbproduct=db.collection("products");

            Product product=new Product(name);

            dbproduct.add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "Not Success", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

