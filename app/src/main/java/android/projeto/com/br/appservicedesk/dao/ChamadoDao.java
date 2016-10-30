package android.projeto.com.br.appservicedesk.dao;

import android.projeto.com.br.appservicedesk.model.Chamado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiquecoelho on 10/11/16.
 */
public class ChamadoDao {

    public boolean abrirChamado(Chamado chamado){
        return true;
    }

    public List<String> consultarChamadoPorFila(int idFila){
        return null;
    }

    public static List<String> consultarTodos(){
        return hardedCodeList();
    }


    private static List<String> hardedCodeList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        return list;
    }

}
