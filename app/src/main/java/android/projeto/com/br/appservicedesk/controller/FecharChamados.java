package android.projeto.com.br.appservicedesk.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.initializer.InitializeSpinner;
import android.projeto.com.br.appservicedesk.util.ConnectionUrls;
import android.projeto.com.br.appservicedesk.util.JSonFacadeFila;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class FecharChamados extends AppCompatActivity {

    public static final String FILA = "android.projeto.com.br.appservicedesk.fila_index";
    private Spinner escolherFila;
    Activity atividade;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_chamados);
        setContentView(R.layout.activity_lista_por_fila);
        escolherFila = (Spinner) findViewById(R.id.escolherFila);
        atividade = this;
        new LoadFilaTask().execute();
    }

    public void listarChamados(View view){
        Intent intent = new Intent(this,FecharChamadosLista.class);
        intent.putExtra(FILA,String.valueOf(escolherFila.getSelectedItemPosition() + 1));
        startActivity(intent);
    }

    class LoadFilaTask extends AsyncTask<String, String, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(atividade, getResources().getString(R.string.aguarde),
                    getResources().getString(R.string.msg_aguarde));
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            return makeJSonRequest();
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            dialog.cancel();
            escolherFila = InitializeSpinner.initializeAndSetAdapterSppinerForFila(escolherFila,atividade,list);

        }

        public ArrayList<String> makeJSonRequest() {
            HttpURLConnection httpcon;
            ArrayList<String> result = null;
            try {

                httpcon = (HttpURLConnection) ((new URL(ConnectionUrls.URL_SERVICO_FILA).openConnection()));
                httpcon.setDoOutput(false);
                httpcon.setRequestMethod("GET");
                httpcon.connect();

                StringBuilder aux = JSonFacadeFila.montaJSon(httpcon);

                result = JSonFacadeFila.jsonToList(aux.toString());

            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return result;
        }
    }



}
