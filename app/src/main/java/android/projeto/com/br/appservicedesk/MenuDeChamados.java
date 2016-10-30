package android.projeto.com.br.appservicedesk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuDeChamados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_de_chamados);
    }

    public void novoChamado(View view){
        Intent intent = new Intent(this,NovoChamado.class);
        startActivity(intent);
    }

    public void consultarChamados(View view){
        Intent intent = new Intent(this,ListarChamados.class);
        startActivity(intent);
    }

    public void fecharChamados(View view){
        Intent intent = new Intent(this,FecharChamados.class);
        startActivity(intent);
    }

}
