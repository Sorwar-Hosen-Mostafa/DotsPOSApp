package com.example.jibunnisa.autocompletewithcustomadapter;

import retrofit2.Call;
import retrofit2.http.POST;


/**
 * Created by Jibunnisa on 4/18/2017.
 */

public interface ProductAPI {


    @POST("ShowData.php")
    Call<Product> getDatas();

}
