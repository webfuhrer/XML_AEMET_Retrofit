package com.example.aplicacionaemet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements PeticionXML.ActualizacionDatos{
Spinner spn_provincias, spn_municipios;
    PeticionXML.ActualizacionDatos a=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spn_provincias=findViewById(R.id.spn_provincias);
        spn_municipios=findViewById(R.id.spn_municipios);
        PeticionXML.pedirProvincias(this);
        spn_provincias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Provincia p=(Provincia) spn_provincias.getSelectedItem();
               PeticionXML.pedirMunicipios(p.getNp(), a);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void mostrarProvincias(Map<String, String> lisat_provincias) {
       ArrayList<Provincia> lista_provincias=new ArrayList();
        for (String clave: lisat_provincias.keySet())
        {
            Provincia p=new Provincia();
            p.setCpine(clave);
            p.setNp(lisat_provincias.get(clave));
            lista_provincias.add(p);
        }
        ArrayAdapter<Provincia> ad=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, lista_provincias);
        spn_provincias.setAdapter(ad);
    }

    @Override
    public void mostrarMunicipios(ClaseMunicipiero m) {
            List<Municipio> lista=m.getLista_municipios();
        ArrayAdapter<Municipio> ad=new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, lista);
        spn_municipios.setAdapter(ad);
    }

    @Override
    public void mostrarClima(ClimaAEMET clima) {

    }
}
