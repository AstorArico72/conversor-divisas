package com.example.conversordemonedas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private Button botonConversion;
    private EditText entradaEuro;
    private EditText entradaDolar;
    private TextView resultadoConversion;
    private MainActivityViewModel vm;
    private char conversion;
    private RadioButton BotonDAE;
    private RadioButton BotonEAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.vm = ViewModelProvider.AndroidViewModelFactory.getInstance (getApplication ()).create (MainActivityViewModel.class);
        this.botonConversion = findViewById(R.id.BotonConvertir);
        this.entradaEuro = findViewById(R.id.Entrada2);
        this.entradaDolar = findViewById(R.id.Entrada1);
        this.resultadoConversion = findViewById(R.id.SalidaResultado);
        this.BotonDAE = findViewById (R.id.DeUsdRadio);
        this.BotonEAD = findViewById (R.id.DeEurRadio);
        this.conversion = 'n';

        BotonDAE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conversion = 'd';
                entradaEuro.setEnabled(false);
                entradaDolar.setEnabled(true);
                entradaEuro.setText("");
            }
        });

        BotonEAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                conversion = 'e';
                entradaDolar.setEnabled(false);
                entradaEuro.setEnabled(true);
                entradaDolar.setText("");
            }
        });

        botonConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.Convertir(entradaDolar.getText().toString(), entradaEuro.getText().toString(), conversion);
            }
        });

        vm.ObtenerConversion().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                resultadoConversion.setText (s);
            }
        });
    }
}