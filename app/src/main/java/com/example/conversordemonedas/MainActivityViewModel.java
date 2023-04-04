package com.example.conversordemonedas;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData <String> conversion = null;

    public MainActivityViewModel(Application application) {
        super(application);
    }
    private Context contexto = getApplication();

    public void Convertir (String dolar, String euro, char aEuroODolar) {
        try {
            double monedaAConvertir;
            switch (aEuroODolar) {
                case 'd':
                    monedaAConvertir = Double.parseDouble(dolar);
                    monedaAConvertir = monedaAConvertir * 0.93;
                    conversion.setValue(monedaAConvertir + "");
                    break;
                case 'e':
                    monedaAConvertir = Double.parseDouble(euro);
                    monedaAConvertir = monedaAConvertir * 1.08;
                    conversion.setValue(monedaAConvertir + "");
                    break;
                case 'n':
                    Toast.makeText(contexto, "No se seleccionó la moneda a convertir o no se ingresó el valor.", Toast.LENGTH_LONG).show();
                    break;
            }
        } catch (Exception e) {
            Toast.makeText(contexto, "Error: Valor en el campo equivocado o no hay valor", Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData <String> ObtenerConversion () {
        this.conversion = new MutableLiveData<>();
        return conversion;
    }
}