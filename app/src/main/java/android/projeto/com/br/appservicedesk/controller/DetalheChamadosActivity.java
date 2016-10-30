package android.projeto.com.br.appservicedesk.controller;

import android.content.Intent;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.model.Chamado;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by caiquecoelho on 10/14/16.
 */
public class DetalheChamadosActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_chamado);
        Intent intent = getIntent();

        TextView numero = (TextView)findViewById(R.id.numero_det);
        TextView descricao = (TextView)findViewById(R.id.descricao_det);
        TextView status = (TextView)findViewById(R.id.status_det);

        numero.setText(intent.getStringExtra(ListaTodosChamados.NUMERO));
        descricao.setText(intent.getStringExtra(ListaTodosChamados.DESCRICAO));
        status.setText(intent.getStringExtra(ListaTodosChamados.STATUS));
    }

}
