package com.example.vedantiladda.ecommerce.product;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.MerchantDTO;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;
import java.util.Map;

public class ProductDetailsAdaptor extends RecyclerView.Adapter<ProductDetailsAdaptor.MerchantHolder> {

    private List<MerchantDTO> merchants;
    private ProductDetailsAdaptor.MerchantCommunicator communicator;

    public ProductDetailsAdaptor(List<MerchantDTO> merchants, MerchantCommunicator communicator) {
        this.merchants = merchants;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public ProductDetailsAdaptor.MerchantHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.merchant_holder, viewGroup,false);

        return new ProductDetailsAdaptor.MerchantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductDetailsAdaptor.MerchantHolder merchantHolder, int i) {
        Log.d("API",merchants.size()+"");
        final MerchantDTO merchant = merchants.get(i);
        merchantHolder.price.setText(merchant.getPrice().toString());
        merchantHolder.merchant.setText(merchant.getName());
        merchantHolder.addCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("APIAD",merchant.getmId()+"");
                Log.d("API",merchant+"");

                String id = merchant.getmId();
                String price = merchant.getPrice().toString();
                communicator.onClickButton(id, price);
            }
        });
    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }

    public class MerchantHolder extends RecyclerView.ViewHolder{
        public TextView merchant, price;
        public Button addCart;
        public MerchantHolder(@NonNull View itemView){
            super(itemView);
            merchant = itemView.findViewById(R.id.merchantname);
            price = itemView.findViewById(R.id.merchantprice);
            addCart = itemView.findViewById(R.id.add_to_cart);

        }
    }

    public interface MerchantCommunicator{
        void onClickButton(String id, String price);
    }
}
