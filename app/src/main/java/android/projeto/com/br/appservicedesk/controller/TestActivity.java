package android.projeto.com.br.appservicedesk.controller;

import android.content.Intent;
import android.projeto.com.br.appservicedesk.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void gotto(View view){
        Intent intent = new Intent(this,MenuDeChamados.class);
        startActivity(intent);
    }
}
