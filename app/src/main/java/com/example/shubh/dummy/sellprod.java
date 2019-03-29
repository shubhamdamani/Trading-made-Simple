package com.example.shubh.dummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class sellprod extends AppCompatActivity {


    ImageView kimg;
    TextView ktit,kpri,kdesc;
    String a,b,c,d;
    Button buy;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellprod);

        kimg=findViewById(R.id.kimg);
        ktit=findViewById(R.id.ktit);
        kpri=findViewById(R.id.kpri);
        kdesc=findViewById(R.id.kdesc);
        buy=findViewById(R.id.buttonbuy);

        a=getIntent().getStringExtra("title");
        b=getIntent().getStringExtra("image");
        c=getIntent().getStringExtra("price");
        d=getIntent().getStringExtra("desc");

        ktit.setText(a);
        kpri.setText(c);
        kdesc.setText(d);

        Picasso.get().load(b.toString()).fit().centerCrop().into(kimg);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();
        final String usr=firebaseAuth.getUid();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("bought").child("tit").setValue(a);
                databaseReference.child("bought").child("pri").setValue(c);
                databaseReference.child("bought").child("desc").setValue(d);

                Intent i=new Intent(sellprod.this,cart.class);

                i.putExtra("a1",a);
                i.putExtra("b1",d);
                i.putExtra("c1",c);
                //i.putExtra("abd",usr);

               // Log.d("dald","adda");
                startActivity(i);






            }
        });




    }
}
