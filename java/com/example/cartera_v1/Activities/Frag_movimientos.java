package com.example.cartera_v1.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.cartera_v1.R;

public class Frag_movimientos extends Fragment {
    ImageView iv_circuloCategoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_movimientos, container, false);
        iv_circuloCategoria = view.findViewById(R.id.iv_circuloCategoria);
        agregarFuncionalidades();
        return view;
    }
    private void agregarFuncionalidades() {
        iv_circuloCategoria.setColorFilter(Color.BLACK);

    }
}
