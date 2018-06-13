package com.example.vedantiladda.ecommerce;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BuyAdaptor extends RecyclerView.Adapter<BuyAdaptor.BuyHolder> {

    private List<Product> products;

    public BuyAdaptor(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public BuyAdaptor.BuyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.buy_layout_item, viewGroup, false);

        return new BuyAdaptor.BuyHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull BuyAdaptor.BuyHolder buyHolder, int i) {

        //set the values.. from

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class BuyHolder extends RecyclerView.ViewHolder {

        TextView txtsno;
        TextView txtproname;
        TextView txtproprice;

        public BuyHolder(View view){
            super(view);
            txtsno =(TextView) itemView.findViewById(R.id.sno);
            txtproname = (TextView) itemView.findViewById(R.id.proname);
            txtproprice = (TextView) itemView.findViewById(R.id.proprice);

        }
    }

}
