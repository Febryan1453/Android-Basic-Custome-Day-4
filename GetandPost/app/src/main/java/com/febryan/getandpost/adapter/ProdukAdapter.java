package com.febryan.getandpost.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.febryan.getandpost.R;
import com.febryan.getandpost.model.DataItem;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.MyViewHolder> {

    List<DataItem> dataItems;
    public ProdukAdapter(List<DataItem> dataItems){
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_row_produk, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvNama.setText(dataItems.get(position).getNamaProduk());

        Locale myIndonesianLocale = new Locale("in", "ID");
        String harga = NumberFormat.getCurrencyInstance(myIndonesianLocale).format(Integer.valueOf(dataItems.get(position).getHarga()));
        holder.tvHarga.setText(harga);

        Picasso.get()
                .load(dataItems.get(position).getGambar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.rusak)
                .into(holder.imgProduk);

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProduk;
        TextView tvNama, tvHarga;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduk = itemView.findViewById(R.id.item_image);
            tvNama = itemView.findViewById(R.id.item_tv_nama);
            tvHarga = itemView.findViewById(R.id.item_tv_harga);

        }
    }
}
