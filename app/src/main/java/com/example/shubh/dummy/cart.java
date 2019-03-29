package com.example.shubh.dummy;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cart extends AppCompatActivity {

    TextView t1,t2,t3;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        t1=findViewById(R.id.tvtv1);
        t3=findViewById(R.id.tvtv3);
        t2=findViewById(R.id.tvtv2);
      //  Log.d("dald","adda");
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
       // progressDialog.setMessage("Updating!");

        Log.d("1234","adda");

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("bought");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                prod p=dataSnapshot.getValue(prod.class);
              /*  try {
                    t1.setText(" "+p.getTit());
                }catch (Exception e){
                    t1.setText("null");
                }
                try{
                    t2.setText(" "+p.getPri());
                }catch (Exception e){
                    t2.setText(" "+"null");
                }
                try{


                t3.setText(p.getDesc());}
                catch(Exception e){
                    t3.setText("null");
                }*/
                String a;
                try{
                    a=p.getPri();
                }
                catch(Exception e)
                {
                    a="null";
                }
                t1.setText(a);

                try{
                    a=p.getTit();
                }
                catch(Exception e)
                {
                    a="null";
                }
                t2.setText(a);

                try{
                    a=p.getDesc();
                }
                catch(Exception e)
                {
                    a="null";
                }
                t3.setText(a);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


       /* t1.setText(getIntent().getStringExtra("a"));
        t3.setText(getIntent().getStringExtra("c"));
        t2.setText(getIntent().getStringExtra("d"));*/

    }
}
