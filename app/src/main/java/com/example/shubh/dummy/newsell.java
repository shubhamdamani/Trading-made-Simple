package com.example.shubh.dummy;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class newsell extends AppCompatActivity {

    private RecyclerView mBlogList;
    private DatabaseReference mDatabase,reford;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;

    EditText esearch;
    Button bsearch;
    // HashMap map=new HashMap();
   // String arr[]=new String[100];

     static String qwe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsell);

        esearch=findViewById(R.id.searchtext);
        bsearch=findViewById(R.id.btnsearch);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("sellwall");


     //  mDatabase = FirebaseDatabase.getInstance().getReference().child("partners");
       //reford = FirebaseDatabase.getInstance().getReference().child("availableorders");
       mBlogList = findViewById(R.id.rview);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

       firebaseSearch("");
     //  final Query fbsq;

       bsearch.setOnClickListener(new View.OnClickListener() {

         //  Query asd;
           @Override
           public void onClick(View v) {

               String txt=esearch.getText().toString();
              //  Query asd=mDatabase.orderByChild("title").;
               firebaseSearch(txt);
           }
           //fbsq=asd;

       });



      //  mDatabase = FirebaseDatabase.getInstance().getReference().child("sellwall");




    }

    private void firebaseSearch(String txt){

        Query fbsearch=mDatabase.orderByChild("title").startAt(txt).endAt(txt+"\uf8ff");


        final FirebaseRecyclerAdapter<Product, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Product, BlogViewHolder>
                (Product.class, R.layout.recview, BlogViewHolder.class, fbsearch) {
            @Override
            protected void populateViewHolder(final BlogViewHolder viewHolder, final Product model, int position) {

                // viewHolder.setId(model.getId());
                viewHolder.setName(model.getTitle());
                viewHolder.setImage(model.getImage());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setShortdesc(model.getShortdesc());
                viewHolder.setRating(model.getRating());



                String a=mDatabase.getRef().toString();


                //  mDatabase.getKey();



                //qwe=model.getImage();


                //viewHolder.mView


            }

            @Override
            public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                final BlogViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                viewHolder.setOnClickListener(new BlogViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // Toast.makeText(newsell.this,"clicked",Toast.LENGTH_SHORT).show();
                        if(position==0)
                        {
                            //  Intent i=new Intent(newsell.this,prodone.class);




                          /*  mDatabase.addChildEventListener(new ChildEventListener() {

                                //  map.clear();
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    //arr.clear();
                                    int j=0;
                                    String parent=dataSnapshot.getKey();

                                    for(DataSnapshot child: dataSnapshot.getChildren()){
                                        //String key=child.g.toString();

                                        String value=child.getValue().toString();
                                        arr[j++]=parent;
                                    }
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
*/  Intent i=new Intent(newsell.this,sellprod.class);
                            //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                            // Toast.makeText(newsell.this,r,Toast.LENGTH_SHORT).show();
                            // String abc=String.valueOf(position);
                            // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                            //
                            // Uri a=Uri.valueOf(position);
                         /*   i.putExtra("data",position);


                            startActivity(i);*/
                            //  getItem(position).get
                            i.putExtra("image",getItem(position).getImage());
                            i.putExtra("title",getItem(position).getTitle());
                            i.putExtra("price",getItem(position).getPrice());
                            i.putExtra("desc",getItem(position).getShortdesc());
                            startActivity(i);
                            //startActivity(i);
                        }
                        if(position==1)
                        {
                            // String r=String.valueOf(viewHolder.getItemId());
                            Intent i=new Intent(newsell.this,sellprod.class);
                            //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                            // Toast.makeText(newsell.this,r,Toast.LENGTH_SHORT).show();
                            // String abc=String.valueOf(position);
                            // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                            //
                            // Uri a=Uri.valueOf(position);
                         /*   i.putExtra("data",position);


                            startActivity(i);*/
                            i.putExtra("image",getItem(position).getImage());
                            i.putExtra("title",getItem(position).getTitle());
                            i.putExtra("price",getItem(position).getPrice());
                            i.putExtra("desc",getItem(position).getShortdesc());
                            startActivity(i);

                        }

                        if(position==2)
                        {
                            // Intent i=new Intent(newsell.this,prodone.class);
                            //String bv=String.valueOf(position);
                            //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                            //   ./ i.putExtra("data1", position);
                            //   String abc=viewHolder.;
                            // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                            //  i.putExtra("data", bv);
                            //  startActivity(i);
                            Intent i=new Intent(newsell.this,sellprod.class);
                            //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                            // Toast.makeText(newsell.this,r,Toast.LENGTH_SHORT).show();
                            // String abc=String.valueOf(position);
                            // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                            //
                            // Uri a=Uri.valueOf(position);
                         /*   i.putExtra("data",position);


                            startActivity(i);*/
                            //  getItem(position).get
                            i.putExtra("image",getItem(position).getImage());
                            i.putExtra("title",getItem(position).getTitle());
                            i.putExtra("price",getItem(position).getPrice());
                            i.putExtra("desc",getItem(position).getShortdesc());
                            startActivity(i);


                        }

                        if(position==3)
                        {
                            Intent i=new Intent(newsell.this,sellprod.class);
                            //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                            // Toast.makeText(newsell.this,r,Toast.LENGTH_SHORT).show();
                            // String abc=String.valueOf(position);
                            // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                            //
                            // Uri a=Uri.valueOf(position);
                         /*   i.putExtra("data",position);


                            startActivity(i);*/
                            //  getItem(position).get
                            i.putExtra("image",getItem(position).getImage());
                            i.putExtra("title",getItem(position).getTitle());
                            i.putExtra("price",getItem(position).getPrice());
                            i.putExtra("desc",getItem(position).getShortdesc());
                            startActivity(i);

                        }

                        Intent i=new Intent(newsell.this,sellprod.class);
                        //  Toast.makeText(newsell.this,position,Toast.LENGTH_SHORT).show();
                        // Toast.makeText(newsell.this,r,Toast.LENGTH_SHORT).show();
                        // String abc=String.valueOf(position);
                        // Toast.makeText(newsell.this,abc,Toast.LENGTH_SHORT).show();
                        //
                        // Uri a=Uri.valueOf(position);
                         /*   i.putExtra("data",position);


                            startActivity(i);*/
                        //  getItem(position).get
                        //   String a=  String.valueOf(getItemId(position));
                        //Toast.makeText(newsell.this,a,Toast.LENGTH_SHORT).show();
                        i.putExtra("image",getItem(position).getImage());
                        i.putExtra("title",getItem(position).getTitle());
                        i.putExtra("price",getItem(position).getPrice());
                        i.putExtra("desc",getItem(position).getShortdesc());
                        startActivity(i);

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


            //ratingBar=mView.findViewById(R.id.ratingBar);

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
            TextView post_name=mView.findViewById(R.id.textViewTitle);
            post_name.setText(name);
        }


        public void setPrice(String price){
            TextView post_name=mView.findViewById(R.id.textViewPrice);
            post_name.setText(price);
        }

        public void setShortdesc(String price){
            TextView post_name=mView.findViewById(R.id.textViewShortDesc);
            post_name.setText(price);
        }
            public void setRating(String price){
            TextView post_name=mView.findViewById(R.id.textViewRating);
            post_name.setText(price);
        }
        public void setId(String price){

        }


        public void setImage(String image){
            ImageView img=mView.findViewById(R.id.imageView);
          //  Picasso.get().load(image.toString()).fit().centerCrop().into(img);
            Picasso.get().load(image.toString()).resize(50,50).centerCrop().into(img);



        }

    }
}
