
package com.example.jibunnisa.autocompletewithcustomadapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("products")
    @Expose
    private List<Product_Info> products = null;

    public List<Product_Info> getProducts() {
        return products;
    }

    public void setProducts(List<Product_Info> products) {
        this.products = products;
    }

}
