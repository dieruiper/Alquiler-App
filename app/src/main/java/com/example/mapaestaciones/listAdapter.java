package com.example.mapaestaciones;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ViewHolder> {
    private ArrayList<Reserva> mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        public TextView r_codigo;
        public TextView r_fechaInicio;
        public TextView r_fechaFin;
        public TextView r_matricula;
        public TextView r_oficina;
        public TextView r_dni;
        Button btn_eliminar_res;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            r_codigo = (TextView) v.findViewById(R.id.r_codigo);
            r_fechaInicio = (TextView) v.findViewById(R.id.r_fechaInicio);
            r_fechaFin = (TextView) v.findViewById(R.id.r_fechaFin);
            r_matricula = (TextView) v.findViewById(R.id.r_matricula);
            r_oficina = (TextView) v.findViewById(R.id.r_oficina);
            r_dni = (TextView) v.findViewById(R.id.r_dni);
            btn_eliminar_res = (Button) v.findViewById(R.id.btn_eliminar_res);

        }

        void setOnClickListeners(){
            btn_eliminar_res.setOnClickListener(this);

        }

        public void onClick(@NotNull View v) {
            switch (v.getId()) {
                case R.id.btn_eliminar_res:


            }
        }




    }


    public listAdapter(ArrayList<Reserva> dataSet) {
        mDataset = dataSet;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lv_verreservas, viewGroup, false);

        return new listAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.r_codigo.setText(mDataset.get(position).getCodigo());
        viewHolder.r_fechaInicio.setText(mDataset.get(position).getFechaInicio());
        viewHolder.r_fechaFin.setText(mDataset.get(position).getFechaFin());
        viewHolder.r_matricula.setText(mDataset.get(position).getMatricula());
        viewHolder.r_oficina.setText(mDataset.get(position).getNombreOficina());
        viewHolder.r_dni.setText(mDataset.get(position).getDni());

        viewHolder.setOnClickListeners();
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
