package com.example.mapaestaciones;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> implements Filterable{

    private ArrayList<Vehiculo> mDataset;
    private ArrayList<Vehiculo> arr;

    /*
    public void filtrado(String txtBuscar) {
        ArrayList<Vehiculo>lista=new ArrayList<Vehiculo>();
        int longitud =txtBuscar.length();
        if(longitud ==0){
            lista.addAll(mDataset);
        }else{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                List<Vehiculo> collection=mDataset.stream()
                        .filter(i -> i.getMarca().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                        collection.toArray();
                        lista.addAll(collection);
            }else{
                for(Vehiculo v:mDataset){
                    if(v.getMarca().toLowerCase().contains(txtBuscar.toLowerCase())){
                        lista.add(v);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }
    */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        public TextView v_marca;
        public TextView v_modelo;
        public TextView v_precio;
        public TextView v_matricula;
        Button btn_seleccionar_vehiculo;
        Button btn_detalles;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            v_marca = (TextView) v.findViewById(R.id.v_marca);
            v_modelo = (TextView) v.findViewById(R.id.v_modelo);
            v_precio = (TextView) v.findViewById(R.id.v_precio);
            v_matricula = (TextView) v.findViewById(R.id.v_matricula);
            btn_seleccionar_vehiculo = (Button) v.findViewById(R.id.btn_seleccionar_vehiculo);
            btn_detalles = (Button) v.findViewById(R.id.btn_detalles);
        }

        void setOnClickListeners(){
            btn_seleccionar_vehiculo.setOnClickListener(this);
            btn_detalles.setOnClickListener(this);
        }

        public void onClick(@NotNull View v) {
            switch (v.getId()) {
                case R.id.btn_seleccionar_vehiculo:
                    Intent i = new Intent(context, Activity_Confirmar_Reserva.class );
                    i.putExtra("matricula_select", v_matricula.getText());
                    i.putExtra("precio", v_precio.getText());
                    context.startActivity(i);
                    break;
                case R.id.btn_detalles:
                    /*
                    Intent r = new Intent(context, Activity_Detalles.class);
                    r.putExtra("matricula_select", v_matricula.getText());
                    context.startActivity(r);
                    break;

                     */
            }
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
        viewHolder.v_matricula.setText(mDataset.get(position).getMatricula());

        viewHolder.setOnClickListeners();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Vehiculo> arrayFiltrado =new ArrayList<Vehiculo>();
            if(charSequence.toString().isEmpty()){
                arrayFiltrado.addAll(mDataset);
            }else{
                for(Vehiculo v :mDataset){
                    if(v.getMarca().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        arrayFiltrado.add(v);
                    }
                }
            }
            FilterResults filterResults= new FilterResults();
            filterResults.values=arrayFiltrado;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataset.clear();
            mDataset.addAll((Collection<? extends Vehiculo>) results.values);
            notifyDataSetChanged();
        }
    };


}


