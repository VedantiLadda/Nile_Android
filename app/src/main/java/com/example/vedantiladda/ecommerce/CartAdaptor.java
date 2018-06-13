package com.example.vedantiladda.ecommerce;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.CartHolder>  {

    private List<Product> products;

    public CartAdaptor(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CartAdaptor.CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);

        return new CartHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.CartHolder cartHolder, int i) {

      //  productHolder.imageView.setI //ask how to set the image
       // productHolder.txtBrand.setText(products.get(i).getProductId); //.get will be from the Product class.

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public class CartHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView txtBrand;
        TextView txtProductName;
        TextView txtPrice;


        public CartHolder(View view){
            super(view);
            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            txtBrand = (TextView) itemView.findViewById(R.id.brand);
            txtProductName = (TextView) itemView.findViewById(R.id.productname);
            txtPrice = (TextView) itemView.findViewById(R.id.price);

        }
    }


}
