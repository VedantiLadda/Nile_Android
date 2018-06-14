package com.example.vedantiladda.ecommerce.product;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        final MerchantDTO merchant = merchants.get(i);
        merchantHolder.price.setText(merchant.getPrice());
        merchantHolder.merchant.setText(merchant.getName());
        merchantHolder.merchant.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = merchant.getId();
                communicator.onClickTextView(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return merchants.size();
    }

    public class MerchantHolder extends RecyclerView.ViewHolder{
        public TextView merchant, price;
        public MerchantHolder(@NonNull View itemView){
            super(itemView);
            merchant = itemView.findViewById(R.id.merchantname);
            price = itemView.findViewById(R.id.merchantprice);

        }
    }

    public interface MerchantCommunicator{
        void onClickTextView(String id);
    }
}
