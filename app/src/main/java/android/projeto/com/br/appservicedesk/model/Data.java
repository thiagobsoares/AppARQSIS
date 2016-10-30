package android.projeto.com.br.appservicedesk.model;


import java.util.ArrayList;

/**
 * Created by asbonato on 9/18/16.
 */
public class Data {
   public static ArrayList<Chamado> _lista;

    public static ArrayList<Chamado> geraListaChamados(){
        if(_lista == null) {
            ArrayList<Chamado> lista = new ArrayList<>();
            lista.add(new Chamado(1L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(2L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(3L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(4L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(5L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(6L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(7L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(8L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(9L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(10L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(11L,"Problema no compiuter",null,null,1,1));
            lista.add(new Chamado(12L,"Problema no compiuter",null,null,1,1));

            _lista = lista;
        }
        return _lista;
    }

    public static ArrayList<Chamado> buscaChamados(){
            ArrayList<Chamado> lista = geraListaChamados();
            return lista;
    }
}

