package com.example.cartera_v1.Adaptadores;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MetasAdapter_Meta extends RecyclerView.Adapter<MetasAdapter_Meta.MetaViewHolder> {
    @NonNull
    @Override
    public MetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MetaViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MetaViewHolder extends RecyclerView.ViewHolder {
        public MetaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
