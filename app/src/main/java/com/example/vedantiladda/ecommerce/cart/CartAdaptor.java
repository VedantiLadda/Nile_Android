package com.example.vedantiladda.ecommerce.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.CartHolder>  {

    private List<ProductDTO> products;
    private CartInterface cartInterface;



    public CartAdaptor(List<ProductDTO> products, CartInterface cartInterface) {
        this.products = products;
        this.cartInterface = cartInterface;
    }

    @NonNull
    @Override
    public CartAdaptor.CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);

        return new CartAdaptor.CartHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CartAdaptor.CartHolder cartHolder, final int i) {

      //  productHolder.imageView.setI //ask how to set the image
       // productHolder.txtBrand.setText(products.get(i).getProductId); //.get will be from the Product class.
        cartHolder.txtBrand.setText(products.get(i).getBrand());
        cartHolder.txtProductName.setText(products.get(i).getName());
        cartHolder.txtPrice.setText(products.get(i).getDefaultMerchantPrice().toString());
        cartHolder.proQuantity.setText(1+"");

        Glide.with(cartHolder.imageView.getContext()).load(products.get(i).getImages().get(0))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(cartHolder.imageView);

        cartHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productId = String.valueOf(products.get(i).getId());
                cartInterface.onClickRemove(productId);
            }
        });


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
        Button removeButton;
        TextView proQuantity;

        public CartHolder(View view){
            super(view);

            imageView = (ImageView) itemView.findViewById(R.id.product_image);
            txtBrand = (TextView) itemView.findViewById(R.id.brand);
            txtProductName = (TextView) itemView.findViewById(R.id.productname);
            txtPrice = (TextView) itemView.findViewById(R.id.price);
            removeButton = (Button) itemView.findViewById(R.id.remove);
            proQuantity = (TextView) itemView.findViewById(R.id.proquantity);

        }
    }

}

interface CartInterface{

    public void onClickRemove(String productId);


}
