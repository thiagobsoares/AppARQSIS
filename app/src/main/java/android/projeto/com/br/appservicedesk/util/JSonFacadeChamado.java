package android.projeto.com.br.appservicedesk.util;

import android.projeto.com.br.appservicedesk.model.Chamado;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by caiquecoelho on 10/14/16.
 */
public class JSonFacadeChamado {

    /*public static StringBuilder montaJSon(Http request)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } finally {
            reader.close();
        }
        return sb;
    }*/

    public static String listToJSon(ArrayList<Chamado> lista) {
        JSONArray vetor = new JSONArray();
        for (Chamado chamado : lista) {
            JSONObject object = new JSONObject();
            try {
                object.put("numero",chamado.getNumero());
                object.put("descricao", chamado.getDescricao());
                object.put("dataFechamento",chamado.getDataDeFechamento().toString());
                object.put("dataAbertura", chamado.getDataAbertura().toString());
                object.put("fila",chamado.getFila());
                object.put("status",chamado.getStatus());
                vetor.put(object);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return vetor.toString();
    }

    public static Chamado jSonToChamado(String json) throws Exception {
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            JSONObject registro = new JSONObject(json);
            long numero = registro.getLong("numero");
            String descricao = registro.getString("descricao");
            Date abertura = null;
            try{
                abertura = dateformat.parse(registro.getString("dataAbertura"));
            }catch(Exception e){
                e.printStackTrace();
                abertura  = null;
            }

            Date fechamento;
            try{
                fechamento = dateformat.parse(registro.getString("dataFechamento"));
            }catch(Exception e){
                fechamento  = null;
            }
            int fila = registro.getInt("fila");
            int status = registro.getInt("status");



            return new Chamado(numero, descricao, fechamento, abertura,status,fila);
        } catch (JSONException jsone) {
            jsone.printStackTrace();
            throw new IOException(jsone);
        }
    }

    public static String chamadoToJSon(Chamado chamado) throws IOException {
        JSONObject object = new JSONObject();
        try {
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
            object.put("numero", chamado.getNumero());
            object.put("descricao", chamado.getDescricao());
            try{
                object.put("dataFechamento", dateformat.format(chamado.getDataDeFechamento()));
            }catch(Exception e){
                object.put("dataFechamento","");
            }
            try {
                object.put("dataAbertura", dateformat.format(chamado.getDataAbertura()));
            }catch(Exception e){
                e.printStackTrace();
            }
            object.put("fila", chamado.getFila());
            object.put("status", chamado.getStatus());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString();
    }


    public static String errorToJSon(Exception e) {
        JSONObject object = new JSONObject();
        try {
            object.put("error", e.toString());
        } catch (JSONException e1) {
            e.printStackTrace();
        }
        return object.toString();
    }

    public static String idFilaToJson(int idFila){
        JSONObject object = new JSONObject();
        try {
            object.put("filaid",idFila);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object.toString                                                                                                 ();
    }
}
