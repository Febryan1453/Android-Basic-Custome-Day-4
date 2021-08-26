package com.febryan.getandpost;

import android.content.Intent;
import android.os.Bundle;

import com.febryan.getandpost.adapter.ProdukAdapter;
import com.febryan.getandpost.api.ApiConfig;
import com.febryan.getandpost.databinding.ActivityMainBinding;
import com.febryan.getandpost.model.DataItem;
import com.febryan.getandpost.model.ResponseProduk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView rvProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(intent);
        });

        rvProduk = findViewById(R.id.rv_produk);
        rvProduk.setHasFixedSize(true);
        rvProduk.setLayoutManager(new GridLayoutManager(this,2));

    }

    @Override
    protected void onResume() {
        super.onResume();

        getData();

    }

    private void getData() {

        ApiConfig.service.getProduk().enqueue(new Callback<ResponseProduk>() {
            @Override
            public void onResponse(Call<ResponseProduk> call, Response<ResponseProduk> response) {

                if (response.isSuccessful()){

                    int responseCode = response.code();
                    Log.d("Response Main", String.valueOf(responseCode));

                    ResponseProduk responseProduk = response.body();
                    List<DataItem> dataItems = responseProduk.getData();

                    ProdukAdapter adapter = new ProdukAdapter(dataItems);
                    rvProduk.setAdapter(adapter);

                }

            }

            @Override
            public void onFailure(Call<ResponseProduk> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}