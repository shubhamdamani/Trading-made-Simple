package com.example.shubh.dummy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{





        //this context we will use to inflate the layout
        private Context mCtx;

        //we are storing all the products in a list
        private List<Product> productList;

        //getting the context and product list with constructor
        public ProductAdapter(Context mCtx, List<Product> productList) {
            this.mCtx = mCtx;
            this.productList = productList;

        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.recview, null);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            //getting the product of the specified position
            Product product = productList.get(position);
            final int y=position;

            //binding the data with the viewholder views
            holder.textViewTitle.setText(product.getTitle());
            holder.textViewShortDesc.setText(product.getShortdesc());
            holder.textViewRating.setText(String.valueOf(product.getRating()));
            holder.textViewPrice.setText(String.valueOf(product.getPrice()));

          //  holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(product.getImage()));
            holder.r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(y==1){
                      Intent i=new Intent(mCtx,prodone.class);
                      mCtx.startActivity(i);

                    }
                    if(y==0){
                        Intent i=new Intent(mCtx,prodone.class);
                        mCtx.startActivity(i);

                    }
                    if(y==2){
                        Intent i=new Intent(mCtx,prodone.class);
                        mCtx.startActivity(i);

                    }





                }
            });

        }


        @Override
        public int getItemCount() {
            return productList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder {

            TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
            ImageView imageView;
            public RelativeLayout r1;

            public ProductViewHolder(View itemView) {
                super(itemView);

                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
                textViewRating = itemView.findViewById(R.id.textViewRating);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
                imageView = itemView.findViewById(R.id.imageView);
                r1=(RelativeLayout)itemView.findViewById(R.id.rel);
            }
        }

}
