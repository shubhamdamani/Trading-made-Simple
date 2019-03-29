package com.example.shubh.dummy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class prodone extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase,reford;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    // HashMap map=new HashMap();
    public int jug=0;
    String arr[]=new String[100];

    static String qwe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prodone);

        mBlogList = findViewById(R.id.rview1);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("sellwall");
       int data=0;
        try {
           data = Integer.parseInt(getIntent().getStringExtra("data"));
        }catch(NumberFormatException e){
            data=0;
        }
        Intent i=getIntent();
        final int d=data;
  //  final int d=Integer.valueOf(getIntent().getStringExtra("data"));


        final FirebaseRecyclerAdapter<Product, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, BlogViewHolder>
                (Product.class, R.layout.jugaad, BlogViewHolder.class, mDatabase) {
            @Override
            protected void populateViewHolder(final BlogViewHolder viewHolder, final Product model, int position) {

                if(position==d)

                {
                    viewHolder.setName(model.getTitle());
                    viewHolder.setImage(model.getImage());
                    viewHolder.setPrice(model.getPrice());
                    viewHolder.setShortdesc(model.getShortdesc());
                    viewHolder.setRating(model.getRating());
                    //viewHolder.getItemId();
                }

            }

            @Override
            public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final BlogViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new BlogViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                    }
                });

                return viewHolder;
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;


            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(v, getAdapterPosition());

                }
            });

        }

        private ClickListener mClickListener;

        public interface ClickListener {
            public void onItemClick(View view, int position);

        }

        public void setOnClickListener(ClickListener clickListener) {
            mClickListener = clickListener;
        }



        public void setName(String name){
            TextView post_name=mView.findViewById(R.id.jtextViewTitle);
            post_name.setText(name);
        }


        public void setPrice(String price){
            TextView post_name=mView.findViewById(R.id.jtextViewPrice);
            post_name.setText(price);
        }

        public void setShortdesc(String price){
            TextView post_name=mView.findViewById(R.id.jtextViewShortDesc);
            post_name.setText(price);
        }
        public void setRating(String price){
            TextView post_name=mView.findViewById(R.id.jtextViewRating);
            post_name.setText(price);
        }
        public void setId(String price){

        }


        public void setImage(String image){
            ImageView img=mView.findViewById(R.id.jimageView);
            Picasso.get().load(image.toString()).fit().centerCrop().into(img);



        }

    }
}
