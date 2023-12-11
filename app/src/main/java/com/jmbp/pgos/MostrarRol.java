package com.jmbp.pgos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MostrarRol extends AppCompatActivity {
    private TextView txtNombreCom, txtCargo, txtSueldoFijo, txtSubsidioAnti, txtHorasExtra, txtSeguroSocial, txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_rol);

        initializeViews();

        Button btnRegresar = findViewById(R.id.btregresa);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarRol.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {
        txtNombreCom = findViewById(R.id.txtNombreEnvio);
        txtCargo = findViewById(R.id.txtCargoEnvio);
        txtSueldoFijo = findViewById(R.id.txtSueldoEnvio);
        txtSubsidioAnti = findViewById(R.id.txtSubcidioEnvio);
        txtHorasExtra = findViewById(R.id.txtHorasEnvio);
        txtSeguroSocial = findViewById(R.id.txtSeguroEnvio);
        txtTotal = findViewById(R.id.txtTotalEnvio);
    }

    private void mostrarResultados(String nombreCompleto, String cargo, double sueldoFijo, double subsidioAnti, double horasExtra, double seguroSocial) {
        setTextViewText(txtNombreCom, nombreCompleto);
        setTextViewText(txtCargo, cargo);
        setTextViewCurrency(txtSueldoFijo, sueldoFijo);
        setTextViewCurrency(txtSubsidioAnti, subsidioAnti);
        setTextViewCurrency(txtHorasExtra, horasExtra);
        setTextViewCurrency(txtSeguroSocial, seguroSocial);

        double total = sueldoFijo + subsidioAnti + horasExtra - seguroSocial;
        setTextViewCurrency(txtTotal, total);
    }

    private void setTextViewText(TextView textView, String text) {
        textView.setText(text);
    }

    private void setTextViewCurrency(TextView textView, double amount) {
        textView.setText(String.format("$%.2f", amount));
    }

    private static double calculaSeguroSocial(double sueldo) {
        return 0.0891 * sueldo;
    }

    private static double calculaHorasExtras(double sueldo, int horas) {
        return (1.0 / 8) * sueldo * horas;
    }

    private static double calculaSubsidioAntiguedad(double sueldo, int antiguedad, int hijos) {
        int sueldoBasico = 420;
        double subsidioPorHijo = 0.02 * sueldoBasico * hijos;
        double subsidioPorAntiguedad = 0.08 * sueldo * antiguedad;
        return subsidioPorHijo + subsidioPorAntiguedad;
    }

    private static double getSueldo(String cargo) {
        switch (cargo.toUpperCase()) {
            case "PROGRAMADOR JUNIOR":
                return 680.0;
            case "PROGRAMADOR SEMI-SENIOR":
                return 980.0;
            case "PROGRAMADOR SENIOR":
                return 1200.0;
            default:
                return 0.0;
        }
    }
}
