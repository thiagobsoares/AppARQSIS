package android.projeto.com.br.appservicedesk.controller;

import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.initializer.InitializeSpinner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class NovoChamado extends AppCompatActivity {

    private Spinner tiposDeFila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        tiposDeFila = (Spinner) findViewById(R.id.tiposDeFila);
        tiposDeFila = InitializeSpinner.initializeAndSetAdapterSppinerForFila(tiposDeFila,this);
    }

}
