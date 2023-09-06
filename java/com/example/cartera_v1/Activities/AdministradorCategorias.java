package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cartera_v1.Adaptadores.VPAdapter;
import com.example.cartera_v1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class AdministradorCategorias extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private VPAdapter adapterViewPager;
    private FloatingActionButton fb_crear_categoria_admin_categorias;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_categorias);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        fb_crear_categoria_admin_categorias = findViewById(R.id.fb_crear_categoria_admin_categorias);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapterViewPager = new VPAdapter(fragmentManager, getLifecycle());

        viewPager.setAdapter(adapterViewPager);
        fb_crear_categoria_admin_categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreacionCategoria.class);
                startActivity(intent);
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

    }
}