package com.example.cartera_v1.Adaptadores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cartera_v1.Activities.Fragments.Frag_categorias_gastos;
import com.example.cartera_v1.Activities.Fragments.Frag_categorias_ingresos;

public class VPAdapter extends FragmentStateAdapter {

    public VPAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new Frag_categorias_gastos();
                break;
            case 1:
                fragment = new Frag_categorias_ingresos();
                break;

            default: assert false : "Error: no hay ningun fragment encontrado";

        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
