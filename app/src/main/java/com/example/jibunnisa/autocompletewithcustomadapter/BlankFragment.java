package com.example.jibunnisa.autocompletewithcustomadapter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    private RecyclerView listView;
    private ArrayList<Product_Info> values;
    String temp="temp";
    private CompoundBarcodeView barcodeView;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        View v;
        v = inflater.inflate(R.layout.fragment_blank, container, false);

        barcodeView = (CompoundBarcodeView) v.findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
        listView = (RecyclerView) getActivity().findViewById(R.id.resultlist);


        values = (ArrayList<Product_Info>) getArguments().getSerializable("valuesArray");



        return v;
    }
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                barcodeView.setStatusText(result.getText());

                if(!result.getText().equals(temp)){

                    for(Product_Info product_info:values){
                        if(product_info.getItemCode().toString().equals(result.getText().toString())){
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.dataslistListView.add(product_info);
                            mainActivity.productListAdapter.notifyDataSetChanged();
                        }
                    }

                 //   values.add(result.toString());
                }
                temp = result.getText();


            }

            //Do something with code result
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public void onResume() {
        barcodeView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        barcodeView.pause();
        super.onPause();
    }


}
