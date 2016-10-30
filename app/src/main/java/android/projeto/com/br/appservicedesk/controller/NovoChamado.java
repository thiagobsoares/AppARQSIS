package android.projeto.com.br.appservicedesk.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.initializer.InitializeSpinner;
import android.projeto.com.br.appservicedesk.model.Chamado;
import android.projeto.com.br.appservicedesk.util.JSonFacadeChamado;
import android.projeto.com.br.appservicedesk.util.JSonFacadeFila;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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


public class NovoChamado extends AppCompatActivity {

    private Spinner tiposDeFila;
    private static String URLJSON = "http://192.168.1.36:8080/servicedesk/ServicoChamado.do";
    private Activity activity;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_chamado);
        activity = this;
        new FilaConverter().execute();
    }


    public void registrarChamado(View view) throws IOException {
        EditText text = (EditText) findViewById(R.id.descricao);
        String descricao = text.getText().toString();
        int fila = tiposDeFila.getSelectedItemPosition() + 1;

        if (descricao != null && !descricao.isEmpty()) {
            Chamado chamado = new Chamado(descricao, 1, fila);//1-estado aberto
            String json = JSonFacadeChamado.chamadoToJSon(chamado);
            new MyDownloadTask().execute(json);

        }else {
            setContentView(R.layout.error_message);
            TextView textView = (TextView) findViewById(R.id.content);
            textView.setText(getResources().getString(R.string.campos_incorretos));
        }
    }


    class MyDownloadTask extends AsyncTask<String, String, Chamado> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(NovoChamado.this, getResources().getString(R.string.aguarde),
                    getResources().getString(R.string.msg_aguarde));
        }

        @Override
        protected Chamado doInBackground(String... params) {
            return makeJSonRequest(params[0]);
        }

        @Override
        protected void onPostExecute(Chamado chamado) {

            dialog.cancel();
            if (chamado != null) {
                if (chamado.getNumero() > 0L) {
                    setContentView(R.layout.error_message);
                    TextView textView = (TextView) findViewById(R.id.content);
                    textView.setText(getResources().getString(R.string.chamado_aberto)
                            + "\n" + getResources().getString(R.string.numero_chamado)
                            + ":"+ chamado.getNumero());
                } else {
                    setContentView(R.layout.error_message);
                    TextView textView = (TextView) findViewById(R.id.content);
                    textView.setText(getResources().getString(R.string.campos_incorretos));
                }
            }


        }

    }


    public Chamado makeJSonRequest(String json) {
        HttpURLConnection httpcon;
        Chamado result = null;
        try {

            httpcon = (HttpURLConnection) ((new URL(URLJSON).openConnection()));
            httpcon.setDoOutput(true);
            /*httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json");*/
            httpcon.setRequestMethod("POST");
            httpcon.connect();

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

   //initialize Spinner
   class FilaConverter extends AsyncTask<String, String, ArrayList<String>> {

        String URLJSON = "http://192.168.1.36:8080/servicedesk/ServicoFila.do";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(NovoChamado.this, getResources().getString(R.string.aguarde),
                    getResources().getString(R.string.msg_aguarde));
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            return makeJSonRequest();
        }

        @Override
        protected void onPostExecute(ArrayList<String> list) {
            dialog.cancel();
            tiposDeFila = (Spinner) findViewById(R.id.tiposDeFila);
            tiposDeFila = InitializeSpinner.initializeAndSetAdapterSppinerForFila(tiposDeFila,activity,list);
        }

        public ArrayList<String> makeJSonRequest() {
            HttpURLConnection httpcon;
            ArrayList<String> result = null;
            try {

                httpcon = (HttpURLConnection) ((new URL(URLJSON).openConnection()));
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
