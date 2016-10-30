package android.projeto.com.br.appservicedesk.controller;

import android.projeto.com.br.appservicedesk.initializer.InitializeSpinner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.widget.Spinner;


public class ListaPorStatus extends AppCompatActivity {

    private Spinner escolherStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_por_status);
        escolherStatus = (Spinner) findViewById(R.id.escolherStatus);
        escolherStatus = InitializeSpinner.initializeAndSetAdapterSppinerForStatus(escolherStatus,this);
    }
}
