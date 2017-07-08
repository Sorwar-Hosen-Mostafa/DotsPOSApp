package com.example.jibunnisa.autocompletewithcustomadapter;

import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private BlankFragment blankFragment;
    private Bundle b = new Bundle();
    AutoCompleteTextView txtSearch;
    List<People> mList;
    ProductAdapter adapter;

    Button scan,closescan;
    ArrayList<String> datas;
    ArrayList<Product_Info> dataslist;
    ArrayList<Product_Info>dataslistListView;
    ArrayAdapter<String > arrayAdapter2;
    RecyclerView recyclerView;
    ProductAPI dataAPI;
    ProductListAdapter productListAdapter;
    String BASE_URL="http://fvpbd.org/PharmacyPOS/main/PHP_FILES/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        scan = (Button) findViewById(R.id.scanbutton);
        closescan = (Button) findViewById(R.id.closescanbutton);

        fragmentManager = getSupportFragmentManager();


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                blankFragment = new BlankFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                blankFragment.setArguments(b);
                fragmentTransaction.add(R.id.container,blankFragment,"frag");
                fragmentTransaction.commit();
            }
        });
        closescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(blankFragment);
                fragmentTransaction.commit();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.resultlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        datas = new ArrayList<>();
        dataslistListView = new ArrayList<>();

        final Product_Info product_info = new Product_Info();
        product_info.setProductCode("pepsi");
        product_info.setPrice("15");
        product_info.setQty("20");
        product_info.setGenName("soft drinks");

        dataslistListView.add(product_info);

        productListAdapter = new ProductListAdapter(dataslistListView,MainActivity.this);
        recyclerView.setAdapter(productListAdapter);

//        arrayAdapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, datas);
//        recyclerView.setAdapter(arrayAdapter2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dataAPI = retrofit.create(ProductAPI.class);

        dataslist = new ArrayList<>();

        getData();

        txtSearch = (AutoCompleteTextView) findViewById(R.id.txtsearch);
        txtSearch.setThreshold(1);

        txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                Product_Info product_info1 = (Product_Info) parent.getItemAtPosition(position);
//                int pos = Arrays.asList(dataslist).indexOf(product_info1);
//
//                Toast.makeText(MainActivity.this,dataslist.get(pos).getGenName(), Toast.LENGTH_SHORT).show();

                dataslistListView.add(dataslist.get(position));
                productListAdapter.notifyDataSetChanged();
                txtSearch.setText("");
            }
        });

    }


    private ArrayList<Product_Info> getData() {
        final ArrayList<Product_Info> products = new ArrayList<>();
        retrofit2.Call<Product> getProductsData = dataAPI.getDatas();
        getProductsData.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(retrofit2.Call<Product> call, Response<Product> response) {
                Product product = response.body();
                List<Product_Info> product_infos;
                product_infos = product.getProducts();

                for (int i =0;i<product_infos.size();i++){
                    products.add(product_infos.get(i));
                }
                setDataslist(products);
            }

            @Override
            public void onFailure(retrofit2.Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this, "not responding", Toast.LENGTH_SHORT).show();
            }
        });

        return products;
    }

    public void setDataslist(ArrayList<Product_Info> dataslist){
        this.dataslist=dataslist;
        // mList = retrievePeople();
        b.putSerializable("valuesArray",dataslist);
        adapter = new ProductAdapter(this, R.layout.activity_main, R.id.lbl_name, dataslist);
        txtSearch.setAdapter(adapter);
    }


    public void setDatas(String datas){
        this.datas.add(datas);
        arrayAdapter2.notifyDataSetChanged();
        txtSearch.setText("");
    }


}
