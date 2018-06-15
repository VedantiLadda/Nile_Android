package com.example.vedantiladda.ecommerce.buy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vedantiladda.ecommerce.R;
import com.example.vedantiladda.ecommerce.model.ProductDTO;

import java.util.List;

public class BuyAdaptor extends RecyclerView.Adapter<BuyAdaptor.BuyHolder> {

    private List<ProductDTO> products;
    private BuyInterface buyInterface;
    public static double priceSum=0;



    public BuyAdaptor(List<ProductDTO> products, BuyInterface buyInterface) {
        this.products = products;
        this.buyInterface = buyInterface;
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

        //buyHolder.txtsno.setText(i);//i is anyway continous numbers...
        buyHolder.txtproname.setText(products.get(i).getProductName());
        buyHolder.txtproprice.setText(products.get(i).getPrice().toString());
        buyHolder.txtsno.setText(i+"");
        double price =  products.get(i).getPrice();
        priceSum = priceSum + price;
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
interface BuyInterface{

//public void priceSum(double pricesum);
}
