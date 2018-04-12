package com.example.tushar.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText phnumber,numplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phnumber=(EditText)findViewById(R.id.phnumber);
        numplate=(EditText)findViewById(R.id.numplate);

        Button update,drive;
        update=(Button)findViewById(R.id.update);
        drive=(Button)findViewById(R.id.driving);

        sharedPreferences=getSharedPreferences("Details",MODE_PRIVATE);
        String phone=sharedPreferences.getString("phone","NULL");
        String plate=sharedPreferences.getString("plate","NULL");


        if(phone.equals("NULL") || plate.equals("NULL")) {

        }
        else
        {
            phnumber.setText(phone);
            numplate.setText(plate);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phone", phnumber.getText().toString());
                editor.putString("plate", numplate.getText().toString());
                editor.commit();

                Toast.makeText(getApplicationContext(),"Details Updated",Toast.LENGTH_LONG).show();
            }
        });

        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        Map<String, Object> user = new HashMap<>();
//        user.put("first", "Tushar");
//        user.put("last", "Mann");
//        user.put("born", 1996);
//
//// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("aag", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("aag", "Error adding document", e);
//                    }
//                });
    }
}
