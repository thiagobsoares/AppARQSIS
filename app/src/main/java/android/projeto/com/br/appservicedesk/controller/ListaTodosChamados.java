package android.projeto.com.br.appservicedesk.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.model.Chamado;
import android.projeto.com.br.appservicedesk.model.ChamadoAdapter;
import android.projeto.com.br.appservicedesk.util.ConnectionUrls;
import android.projeto.com.br.appservicedesk.util.JSonFacadeChamado;
import android.projeto.com.br.appservicedesk.util.JSonFacadeFila;
import android.projeto.com.br.appservicedesk.util.StatusHandler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;


public class ListaTodosChamados extends AppCompatActivity {

    public static final String NUMERO = "android.projeto.com.br.appservicedesk.numero_det";
    public static final String DESCRICAO = "android.projeto.com.br.appservicedesk.descricao_det";
    public static final String STATUS = "android.projeto.com.br.appservicedesk.status_det";
    private ProgressDialog dialog;
    Activity atividade;
    ArrayList<Chamado> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_todos_chamados);
        atividade = this;
        Intent intent = getIntent();
        new LoadAllChamadosTask().execute();

    }

    class LoadAllChamadosTask extends AsyncTask<String, String, ArrayList<Chamado>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(atividade, getResources().getString(R.string.aguarde),
                    getResources().getString(R.string.msg_aguarde));
        }

        @Override
        protected ArrayList<Chamado> doInBackground(String... params) {
            return makeJSonRequest();
        }

        @Override
        protected void onPostExecute(ArrayList<Chamado> list) {
            dialog.cancel();
            lista = list;

            BaseAdapter adapter = new ChamadoAdapter(atividade, lista);
            ListView listView = (ListView) findViewById(R.id.lista_chamados);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // manda para a tela de detalhe
                    Intent intent = new Intent(atividade, DetalheChamadosActivity.class);
                    intent.putExtra(NUMERO, String.valueOf(lista.get(position).getNumero()));
                    intent.putExtra(DESCRICAO, lista.get(position).getDescricao());
                    intent.putExtra(STATUS, StatusHandler.status.get(lista.get(position).getStatus()));

                    startActivity(intent);

                }

            });
        }

        public ArrayList<Chamado> makeJSonRequest() {
            HttpURLConnection httpcon;
            ArrayList<Chamado> result = null;
            try {

                httpcon = (HttpURLConnection) ((new URL(ConnectionUrls.URL_SERVICO_CHAMADO).openConnection()));
                httpcon.setDoOutput(false);
                httpcon.setRequestMethod("GET");
                httpcon.connect();

                StringBuilder aux = JSonFacadeFila.montaJSon(httpcon);

                result = JSonFacadeChamado.jsonToList(aux.toString());

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
