package com.example.jibunnisa.autocompletewithcustomadapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jibunnisa on 7/5/2017.
 */

public class ProductListAdapter  extends RecyclerView.Adapter<ProductListAdapter.DerpHolder>{

    List<Product_Info> data ;
    LayoutInflater inflater;
    ClickedListener clickedListener;
    Context context;
    public ProductListAdapter(List<Product_Info> data, Context context){

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }
    public void setClickedListener(ClickedListener clickedListener){
        this.clickedListener = clickedListener;
    }

    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_layout_listview,parent,false);

        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {

        final Product_Info item = data.get(position);
        holder.itemname.setText(item.getProductCode());
        holder.quantity.setText(item.getQty());
        holder.size.setText(item.getGenName());
        holder.sellingprice.setText(item.getPrice());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,item.getGenName(), Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(context,EventDescriptionActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("title",item.getTitle());
//                i.putExtra("description",item.getDescription());
//                i.putExtra("image",item.getPicture());
//                i.putExtra("deadline","Dead Line: "+item.getDeadline().toString());
//                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView itemname;
        TextView sellingprice;
        TextView quantity;
        TextView size;
        View view;

        public DerpHolder(View itemView) {
            super(itemView);
            itemname = (TextView) itemView.findViewById(R.id.item_name);
            size = (TextView) itemView.findViewById(R.id.size);
            sellingprice = (TextView) itemView.findViewById(R.id.selling_price);
            view = itemView.findViewById(R.id.count_item_root);
            quantity = (TextView) itemView.findViewById(R.id.size);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(clickedListener!=null){
                clickedListener.itemclick(v,getPosition());
            }
        }
    }
    public interface ClickedListener{
        public void itemclick(View view, int position);
    }
}
