package com.example.cartera_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cartera_v1.Activities.Fragments.Frag_ajustes;
import com.example.cartera_v1.Activities.Fragments.Frag_billeteras;
import com.example.cartera_v1.Activities.Fragments.Frag_metas;
import com.example.cartera_v1.Activities.Fragments.Frag_movimientos;
import com.example.cartera_v1.Activities.Fragments.Frag_recordatorios;
import com.example.cartera_v1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        remplazarFragmento(new Frag_movimientos());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.movimientos:
                    remplazarFragmento(new Frag_movimientos());
                    break;
                case R.id.billeteras:
                    remplazarFragmento(new Frag_billeteras());
                    break;
                case R.id.metas:
                    remplazarFragmento(new Frag_metas());
                    break;
                case R.id.recordatorios:
                    remplazarFragmento(new Frag_recordatorios());
                    break;
                case R.id.ajustes:
                    remplazarFragmento(new Frag_ajustes());
                    break;
            }
            return true;
        });
    }

    private void remplazarFragmento(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutMA,fragment);
        ft.commit();
    }

}