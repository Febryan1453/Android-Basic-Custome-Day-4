package com.febryan.getandpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.febryan.getandpost.api.ApiConfig;
import com.febryan.getandpost.databinding.ActivityInsertBinding;
import com.febryan.getandpost.model.ResponseActionProduk;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnInsert.setOnClickListener(v -> {

            String nama = binding.edtNamaProduk.getText().toString();
            String harga = binding.edtHarga.getText().toString();
            String urlGambar = binding.edtGambar.getText().toString();

            if (nama.isEmpty()){
                Toast.makeText(this, "Nama produk masih kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (harga.isEmpty()){
                Toast.makeText(this, "Harga masih kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (urlGambar.isEmpty()){
                Toast.makeText(this, "Gambar masih kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            insertData(nama, harga, urlGambar);

        });

    }

    private void insertData(String nama, String harga, String urlGambar) {

        ApiConfig.service.insertProduk(nama, harga, urlGambar).enqueue(new Callback<ResponseActionProduk>() {
            @Override
            public void onResponse(Call<ResponseActionProduk> call, Response<ResponseActionProduk> response) {
                if (response.isSuccessful()){

                    int responseCode = response.code();
                    Log.d("Insert", String.valueOf(responseCode));

                    ResponseActionProduk responseActionProduk = response.body();

                    if (responseActionProduk.getStatus().equals("Success")){

                        String pesan = responseActionProduk.getMessage();
                        Log.d("Insert", pesan);

                        finish();

                        Toast toast =  Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();

                    }else {

                        String pesan = responseActionProduk.getMessage();
                        Log.d("Insert", pesan);
//
                        Toast toast =  Toast.makeText(InsertActivity.this, pesan, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();

                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseActionProduk> call, Throwable t) {
                Toast.makeText(InsertActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}