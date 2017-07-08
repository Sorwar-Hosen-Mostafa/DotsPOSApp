
package com.example.jibunnisa.autocompletewithcustomadapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product_Info implements Serializable {

    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("product_code")
    @Expose
    private String productCode;
    @SerializedName("gen_name")
    @Expose
    private String genName;
    @SerializedName("Item_code")
    @Expose
    private String itemCode;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("cost")
    @Expose
    private String cost;
    @SerializedName("o_price")
    @Expose
    private String oPrice;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("profit")
    @Expose
    private String profit;
    @SerializedName("supplier")
    @Expose
    private String supplier;
    @SerializedName("onhand_qty")
    @Expose
    private String onhandQty;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("qty_sold")
    @Expose
    private String qtySold;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("date_arrival")
    @Expose
    private String dateArrival;
    @SerializedName("photo")
    @Expose
    private String photo;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getGenName() {
        return genName;
    }

    public void setGenName(String genName) {
        this.genName = genName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getOPrice() {
        return oPrice;
    }

    public void setOPrice(String oPrice) {
        this.oPrice = oPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getOnhandQty() {
        return onhandQty;
    }

    public void setOnhandQty(String onhandQty) {
        this.onhandQty = onhandQty;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getQtySold() {
        return qtySold;
    }

    public void setQtySold(String qtySold) {
        this.qtySold = qtySold;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(String dateArrival) {
        this.dateArrival = dateArrival;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
