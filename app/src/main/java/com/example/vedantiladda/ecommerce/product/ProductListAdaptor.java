package com.example.vedantiladda.ecommerce.product;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.vedantiladda.ecommerce.LaunchAdaptor;
import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.Category;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;

public class ProductListAdaptor extends RecyclerView.Adapter<ProductListAdaptor.ProductListHolder> {

    private List<ProductDTO> products;
    private ProductListAdaptor.ProductListCommunicator communicator;

    public ProductListAdaptor(List<ProductDTO> products, ProductListAdaptor.ProductListCommunicator communicator) {
        this.products = products;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public ProductListAdaptor.ProductListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_list_holder, viewGroup,false);

        return new ProductListAdaptor.ProductListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductListAdaptor.ProductListHolder productListHolder, int i) {
        final ProductDTO product = products.get(i);
        Glide.with(productListHolder.image.getContext()).load(products.get(i).getImageURL())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(productListHolder.image);
        productListHolder.description.setText(product.getProductDescription());
        productListHolder.name.setText(product.getProductName());
        productListHolder.price.setText(product.getPrice());
        productListHolder.constraint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ProductDTO product = (products.get(productListHolder.getAdapterPosition()));
                String id = product.getProductId();
                communicator.onClickTextView(id);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductListHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout constraint;
        public ImageView image;
        public TextView name, price, description;
        public ProductListHolder(@NonNull View itemView) {
            super(itemView);
            constraint = itemView.findViewById(R.id.Product);
            image = itemView.findViewById(R.id.Product1Image);
            name = itemView.findViewById(R.id.Product1);
            description = itemView.findViewById(R.id.Product1Description);
            price = itemView.findViewById(R.id.Product1Price);

        }
    }

    public interface ProductListCommunicator{
        public void onClickTextView(String id);
    }
}
