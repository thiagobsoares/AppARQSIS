package android.projeto.com.br.appservicedesk.controller;

import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.initializer.InitializeSpinner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class ListarChamados extends AppCompatActivity {

    private Spinner escolherFila,escolherStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamados);
    }

    public void listarPorFila(View view){
        setContentView(R.layout.escolher_fila);
        escolherFila = (Spinner) findViewById(R.id.escolherFila);
        escolherFila = InitializeSpinner.initializeAndSetAdapterSppinerForFila(escolherFila,this);
    }

    public void listarPorStatus(View view){
        setContentView(R.layout.escolher_status);
        escolherStatus = (Spinner) findViewById(R.id.escolherStatus);
        escolherStatus = InitializeSpinner.initializeAndSetAdapterSppinerForStatus(escolherStatus,this);
    }

    public void listarTodosChamados(View view){

    }
}
