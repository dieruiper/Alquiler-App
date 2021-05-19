package com.example.mapaestaciones;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    private ArrayList<Vehiculo> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView v_marca;
        public TextView v_modelo;
        public TextView v_precio;

        public ViewHolder(View v) {
            super(v);
            v_marca = (TextView) v.findViewById(R.id.v_marca);
            v_modelo = (TextView) v.findViewById(R.id.v_modelo);
            v_precio = (TextView) v.findViewById(R.id.v_precio);
        }
    }

    public VehiculoAdapter(ArrayList<Vehiculo> dataSet) {
        mDataset = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehiculo, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.v_marca.setText(mDataset.get(position).getMarca());
        viewHolder.v_modelo.setText(mDataset.get(position).getModelo());
        viewHolder.v_precio.setText(mDataset.get(position).getPrecio().toString());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


