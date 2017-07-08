package com.example.jibunnisa.autocompletewithcustomadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Jibunnisa on 7/5/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product_Info> {
    Context context;
    int resource, textViewResourceId;
    List<Product_Info> items, tempItems, suggestions;


    public ProductAdapter(Context context, int resource, int textViewResourceId, List<Product_Info> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Product_Info>(items); // this makes the difference.
        suggestions = new ArrayList<Product_Info>();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.rowlayout, parent, false);
        }
        Product_Info people = items.get(position);
        if (people != null) {
            TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
            if (lblName != null)
                lblName.setText(people.getProductCode()+" "+people.getGenName()+" "+people.getProductName()+" "+people.getItemCode()+" "+people.getPrice());
        }
        return view;
    }




    @NonNull
    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Product_Info) resultValue).getProductCode()+" "+((Product_Info) resultValue).getGenName()+" "+((Product_Info) resultValue).getProductName()
                    +" "+((Product_Info) resultValue).getItemCode()+" "+((Product_Info) resultValue).getPrice();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Product_Info product_info : tempItems) {
                    String str = product_info.getProductCode()+" "+product_info.getGenName()+" "+product_info.getProductName()+" "+product_info.getItemCode()+" "+product_info.getPrice();
                    if (str.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(product_info);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Product_Info> filterList = (ArrayList<Product_Info>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Product_Info people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
