package com.example.shubh.dummy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class register extends AppCompatActivity {


    private EditText userName, userPassword, userEmail, userAge;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ImageView userProfilePic;
    String email, name, age, password;
    private FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private ProgressDialog progressDialog;
    private static int PICK_IMAGE = 123;
    Uri imagePath;
    private StorageReference storageReference;



 /*   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data.getData() != null){
            imagePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
                userProfilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);


  /*      storageReference = firebaseStorage.getReference();

        userProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });
*/


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //Upload data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.setMessage("Making Progess!");
                            progressDialog.show();
                            if(task.isSuccessful()){
                                sendEmailVerification();
                              progressDialog.dismiss();

                                sendUserData();
                                firebaseAuth.signOut();
                                Toast.makeText(register.this, "Successfully Registered, Upload complete!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(register.this, MainActivity.class));
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(register.this, MainActivity.class));
            }
        });

    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView)findViewById(R.id.tvUserLogin);
        userAge = (EditText)findViewById(R.id.etAge);
      //  userProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private Boolean validate(){
        Boolean result = false;

        name = userName.getText().toString();
        password = userPassword.getText().toString();
        email = userEmail.getText().toString();
        age = userAge.getText().toString();


        if(name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty() ){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }

        return result;
    }


    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                       sendUserData();
                        Toast.makeText(register.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();

                    //    finish();
                        startActivity(new Intent(register.this, MainActivity.class));
                        firebaseAuth.signOut();
                    }else{
                        Toast.makeText(register.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

   private void sendUserData(){

       firebaseDatabase = FirebaseDatabase.getInstance();
       try
       {
           myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
       }catch(Exception e){
           Toast.makeText(register.this,"ok",Toast.LENGTH_SHORT).show();
       }

        Toast.makeText(register.this,firebaseAuth.getUid(),Toast.LENGTH_SHORT).show();

        Log.d("Damani","Call received");
       name = userName.getText().toString();
       password = userPassword.getText().toString();
       email = userEmail.getText().toString();
       age = userAge.getText().toString();

       //StorageReference imageReference = storageReference.child(firebaseAuth.getUid()).child("Images").child("Profile Pic");  //User id/Images/Profile Pic.jpg
      //  UploadTask uploadTask = imageReference.putFile(imagePath);
       /* uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(register.this, "Upload failed!", Toast.LENGTH_SHORT).show();
            }
        }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                Toast.makeText(register.this, "Upload successful!", Toast.LENGTH_SHORT).show();
            }
        });*/
        UserProfile userProfile = new UserProfile(age, email, name);
        myRef.setValue(userProfile);
        myRef.child("password/pwd").setValue(password);
     /*  DatabaseReference refuser=FirebaseDatabase.getInstance().getReference().push();
       refuser.child("name").setValue(name);
       refuser.child("age").setValue(age);
       refuser.child("email").setValue(email);*/


    }
}








