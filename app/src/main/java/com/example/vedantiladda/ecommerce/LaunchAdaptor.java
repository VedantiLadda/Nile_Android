package com.example.vedantiladda.ecommerce;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.vedantiladda.ecommerce.model.Category;

import java.util.List;

public class LaunchAdaptor extends RecyclerView.Adapter<LaunchAdaptor.CategoryHolder> {

    private List<Category> categories;
    private LaunchCommunicator communicator;

    public LaunchAdaptor(List<Category> category, LaunchCommunicator communicator){
        this.categories = category;
        this.communicator = communicator;
    }

    @NonNull
    @Override
    public LaunchAdaptor.CategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.launch_holder, viewGroup,false);

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LaunchAdaptor.CategoryHolder categoryHolder, int i) {
        final Category category = categories.get(i);
        categoryHolder.categoryName.setText(category.getCategoryName());
        categoryHolder.categoryName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Category category = categories.get(categoryHolder.getAdapterPosition());
                String id=category.getCategoryId();
                communicator.onClickTextView(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder{
        public TextView categoryName;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = (TextView) itemView.findViewById(R.id.Category);

        }
    }
    public interface LaunchCommunicator {
        public void onClickTextView(String id);
    }
}
