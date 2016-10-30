package android.projeto.com.br.appservicedesk.controller;

import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

public class ListaPorFila extends AppCompatActivity {

    private Spinner escolherFila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_por_fila);
        escolherFila = (Spinner) findViewById(R.id.escolherFila);
        //escolherFila = InitializeSpinner.initializeAndSetAdapterSppinerForFila(escolherFila,this);
    }

    public void listarChamados(View view){
        escolherFila.getSelectedItem();
    }

}
