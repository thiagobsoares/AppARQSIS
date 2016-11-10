package android.projeto.com.br.appservicedesk.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.model.Chamado;
import android.projeto.com.br.appservicedesk.model.ChamadoAdapter;
import android.projeto.com.br.appservicedesk.util.ConnectionUrls;
import android.projeto.com.br.appservicedesk.util.JSonFacadeChamado;
import android.projeto.com.br.appservicedesk.util.JSonFacadeFila;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class FecharChamadosLista extends AppCompatActivity {

    public static final String NUMERO = "android.projeto.com.br.appservicedesk.numero_det";
    public static final String DESCRICAO = "android.projeto.com.br.appservicedesk.descricao_det";
    public static final String STATUS = "android.projeto.com.br.appservicedesk.status_det";
    Activity atividade;
    ArrayList<Chamado> lista;
    private ProgressDialog dialog;
    private int idFila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_chamados_lista);
        atividade = this;
        Intent intent = getIntent();
        idFila = Integer.parseInt(intent.getStringExtra(FecharChamados.FILA));
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
            ListView listView = (ListView) findViewById(R.id.fecha_chamados);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {

                    new AlertDialog.Builder(atividade)
                            .setTitle(R.string.fechar_chamado)
                            .setMessage(R.string.fechar_chamado_confirmation)
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    final Chamado chamadoClose = lista.get(position);
                                    chamadoClose.setDataDeFechamento(new Date());

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            makeJSonRequestForChamado(chamadoClose);
                                        }
                                    }){}.start();

                                    atividade.recreate();
                                }
                            })
                            .setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

            });

        }

        public ArrayList<Chamado> makeJSonRequest() {
            HttpURLConnection httpcon;
            ArrayList<Chamado> result = null;
            try {

                httpcon = (HttpURLConnection) ((new URL(ConnectionUrls.URL_SERVICO_CHAMADO +
                        "?chave=" + idFila).openConnection()));
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

    public Chamado makeJSonRequestForChamado(Chamado chamadoClose) {
        HttpURLConnection httpcon;
        Chamado result = null;
        try {

            httpcon = (HttpURLConnection) ((new URL(ConnectionUrls.URL_SERVICO_CHAMADO).openConnection()));
            httpcon.setDoOutput(true);
            httpcon.setRequestMethod("PUT");
            httpcon.connect();

            String json = JSonFacadeChamado.chamadoToJSon(chamadoClose);
            //Write
            OutputStream os = httpcon.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(json);
            writer.close();
            os.close();

            //Read
            BufferedReader br = new BufferedReader(new InputStreamReader(httpcon.getInputStream(), "UTF-8"));

            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            br.close();

            result = JSonFacadeChamado.jSonToChamado(sb.toString());


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
