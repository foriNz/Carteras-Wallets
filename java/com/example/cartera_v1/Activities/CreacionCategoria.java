package com.example.cartera_v1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.cartera_v1.BBDD.BDCategorias;
import com.example.cartera_v1.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreacionCategoria extends AppCompatActivity {
    ImageView btn_atras, btn_guardar_borrar;
    RadioGroup rg_colores, rg_iconos;
    EditText nombre_categoria;
    CircleImageView icono_seleccion;
    TableLayout tl_iconos_categorias, tl_colores_categorias;
    int idRecursoIconoSeleccionado;
    String  color_seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creacion_categoria);
        agregarFuncionalidades();
    }

    private void agregarFuncionalidades() {
        btn_atras = findViewById(R.id.btn_atras_creacion_categoria);
        btn_guardar_borrar = findViewById(R.id.btn_guardar_borrar_categoria);
        nombre_categoria = findViewById(R.id.et_nombre_categoria_creacion);
        icono_seleccion = findViewById(R.id.civ_icono_seleccionado);
        tl_iconos_categorias = findViewById(R.id.tl_iconos_categorias);
        tl_colores_categorias = findViewById(R.id.tl_colores_categorias);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_guardar_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 04/09/2023 BOTON GUARDAR/BORRAR CATEGORI
                BDCategorias bdCategorias = new BDCategorias(CreacionCategoria.this);
                bdCategorias.addCategoria(nombre_categoria.getText().toString(),
                        color_seleccion,
                        String.valueOf(idRecursoIconoSeleccionado), "gasto");
                finish();
            }
        });
        rellenarColores();
        rellenarIconos();
    }

    private void rellenarIconos() {
        ArrayList<String> iconos = getIconos();
        TableRow tr = null;
        for (int i = 0; i < iconos.size(); i++) {
            if (i % 5 == 0) {
                tr = new TableRow(this);
                tl_iconos_categorias.addView(tr);
            }
            LayoutInflater inflater = this.getLayoutInflater();
            ImageView imageView;
            View view = inflater.inflate(R.layout.view_cardview_categoria, null);
            imageView = view.findViewById(R.id.iv_cardview_icono_categoria);
            imageView.getLayoutParams().height = 200;
            imageView.getLayoutParams().width = 200;
            int a = getResources().getIdentifier(iconos.get(i),
                    "drawable", this.getPackageName());
            imageView.setBackgroundResource(a);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icono_seleccion.setImageResource(a);
                    idRecursoIconoSeleccionado = getResources().getIdentifier(getResources().getResourceEntryName(a), "drawable", getPackageName());

                }
            });
            if (tr != null)
                tr.addView(view);

        }
    }

    private ArrayList<String> getIconos() {
        ArrayList<String> resultado = new ArrayList();
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_categorias_mando));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_attach_money_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_money_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_wallet_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.baseline_drive_eta_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_currency_bitcoin_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_add_business_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_cabeza_interrogacion));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_child_friendly_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_coffee_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_construction_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_cottage_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_cubiertos));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_cumpleanios));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_diamond_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_directions_transit_filled_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_edit_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_euro_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_festival_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_fitness_center_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_house_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_laptop_windows_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_liquor_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_live_tv_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_local_laundry_service_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_local_phone_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_menu_book_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_music_note_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_payments_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_pedal_bike_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_power_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_security_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_social));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_spa_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_sports_motorsports_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_subway_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_tag_faces_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_two_wheeler_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_videocam_24));
        resultado.add(getResources().getResourceEntryName(R.drawable.outline_vaping_rooms_24));


        return resultado;
    }

    private String[] getColores() {
        return this.getResources().getStringArray(R.array.colores);
    }

    private void rellenarColores() {
        String[] colores = getColores();

        TableRow tr = new TableRow(this);
        for (int i = 0; i < colores.length; i++) {

            LayoutInflater inflater = this.getLayoutInflater();
            CircleImageView imageView;
            View view = inflater.inflate(R.layout.view_circulo_colores, null);
            imageView = view.findViewById(R.id.civ_cardview_colores_categoria);
            imageView.getLayoutParams().height = 70;
            imageView.getLayoutParams().width = 70;
            String b = colores[i];
            int a = Color.parseColor(colores[i]);
            imageView.setBackgroundColor(a);
            //imageView.setColorFilter(i);
            // imageView.setBackgroundColor();
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    icono_seleccion.setColorFilter(a);
                    icono_seleccion.setBorderColor(a);
                    color_seleccion = b;


                }
            });
            tr.addView(view);
        }
        tl_colores_categorias.addView(tr);
    }
}