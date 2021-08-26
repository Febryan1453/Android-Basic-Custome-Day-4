package com.febryan.getandpost.api;

import com.febryan.getandpost.model.ResponseActionProduk;
import com.febryan.getandpost.model.ResponseProduk;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("produk")
    Call<ResponseProduk> getProduk();

    @FormUrlEncoded
    @POST("AddProduk")
    Call<ResponseActionProduk> insertProduk(@Field("nama_produk") String nama,
                                            @Field("harga") String harga,
                                            @Field("gambar") String gambar);

}
