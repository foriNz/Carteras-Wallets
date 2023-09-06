package com.example.cartera_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.cartera_v1.Activities.Fragments.Ajustes;
import com.example.cartera_v1.Activities.Fragments.Billeteras;
import com.example.cartera_v1.Activities.Fragments.Metas;
import com.example.cartera_v1.Activities.Fragments.Cronologia;
import com.example.cartera_v1.Activities.Fragments.Recordatorios;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        remplazarFragmento(new Cronologia());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.movimientos:
                    remplazarFragmento(new Cronologia());
                    break;
                case R.id.billeteras:
                    remplazarFragmento(new Billeteras());
                    break;
                case R.id.metas:
                    remplazarFragmento(new Metas());
                    break;
                case R.id.recordatorios:
                    remplazarFragmento(new Recordatorios());
                    break;
                case R.id.ajustes:
                    remplazarFragmento(new Ajustes());
                    break;
            }
            return true;
        });
    }
    public void redirigitAFragmento(int indice) {
        switch(indice) {
            case 0:
                remplazarFragmento(new Cronologia());
                break;
            case 1:
                remplazarFragmento(new Billeteras());
                break;
            case 2:
                remplazarFragmento(new Metas());
                break;
            case 3:
                remplazarFragmento(new Recordatorios());
                break;
            case 4:
                remplazarFragmento(new Ajustes());
                break;
            default:
                assert false;
        }
    }

    private void remplazarFragmento(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayoutMA,fragment);
        ft.commit();
    }

}