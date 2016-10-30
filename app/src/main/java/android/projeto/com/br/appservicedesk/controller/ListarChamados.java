package android.projeto.com.br.appservicedesk.controller;

import android.app.Activity;
import android.content.Intent;
import android.projeto.com.br.appservicedesk.R;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class ListarChamados extends AppCompatActivity {

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_chamados);
        activity = this;
    }


    public void listarPorFila(View view){
        Intent intent = new Intent(this,ListaPorFila.class);
        startActivity(intent);
    }

    public void listarPorStatus(View view){
        Intent intent = new Intent(this,ListaPorStatus.class);
        startActivity(intent);
    }

    public void listarTodosChamados(View view){
        Intent intent = new Intent(this,ListaTodosChamados.class);
        startActivity(intent);
    }
}
