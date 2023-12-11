package com.jmbp.pgos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edNombre = findViewById(R.id.txtNombre);
        EditText edAnios = findViewById(R.id.txtAnios);
        EditText edHijos = findViewById(R.id.txtHijos);
        EditText edHoras = findViewById(R.id.txtHoras);
        Button btnCalcular = findViewById(R.id.btnCalcular);

        Spinner cbxCargos = findViewById(R.id.cbxCargos);
        String[] opCargos = {"PROGRAMADOR JUNIOR", "PROGRAMADOR SEMI-SENIOR", "PROGRAMADOR SENIOR"};
        ArrayAdapter<String> cargosEmpresa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opCargos);
        cbxCargos.setAdapter(cargosEmpresa);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MostrarRol.class);
                Bundle datos = new Bundle();

                String nombreCompleto = Objects.requireNonNull(edNombre.getText()).toString();
                String aniosStr = Objects.requireNonNull(edAnios.getText()).toString();
                String hijosStr = Objects.requireNonNull(edHijos.getText()).toString();
                String horasStr = Objects.requireNonNull(edHoras.getText()).toString();
                String cargo = cbxCargos.getSelectedItem().toString();

                try {
                    Integer nanio = Integer.parseInt(aniosStr);
                    Integer nHijos = Integer.parseInt(hijosStr);
                    Integer nHoras = Integer.parseInt(horasStr);

                    if (!TextUtils.isEmpty(nombreCompleto) && nanio != null && nHijos != null && nHoras != null && !TextUtils.isEmpty(cargo)) {
                        Toast.makeText(MainActivity.this, "CALCULANDO SU SALARIO", Toast.LENGTH_SHORT).show();
                        datos.putString("nombreC", nombreCompleto);
                        datos.putInt("nanios", nanio);
                        datos.putInt("nhijos", nHijos);
                        datos.putInt("nhoras", nHoras);
                        datos.putString("cargo", cargo);
                        intent.putExtras(datos);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "INGRESE DATOS", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "ASEGURECE DE LLENAR BIEN LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}