package android.projeto.com.br.appservicedesk.model;

import android.app.Activity;
import android.content.Context;
import android.projeto.com.br.appservicedesk.R;
import android.projeto.com.br.appservicedesk.util.StatusHandler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by caiquecoelho on 10/14/16.
 */
public class ChamadoAdapter extends BaseAdapter implements SectionIndexer {


    Activity context;
    ArrayList<Chamado> chamados;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public ChamadoAdapter(Activity context, ArrayList<Chamado> chamados){
        this.context = context;
        this.chamados = chamados;
        sectionHeaders = SectionIndexBuilder.BuildSectionHeaders(chamados);
        positionForSectionMap = SectionIndexBuilder.BuildPositionForSectionMap(chamados);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(chamados);

    }

    @Override
    public int getCount() {
        return chamados.size();
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < chamados.size()){
            return chamados.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.linha_chamado, parent, false);

            TextView numeroChamado = (TextView)view.findViewById(R.id.numero_chamado);
            TextView descricaoChamado = (TextView)view.findViewById(R.id.descricao_chamado);
            TextView statusChamdo = (TextView)view.findViewById(R.id.status_chamado);
            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(numeroChamado, descricaoChamado, statusChamdo));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Locale locale = new Locale("pt", "BR");
        holder.getNumeroChamado().setText(String.valueOf(chamados.get(position).getNumero()));
        holder.getDescricaoChamado().setText(chamados.get(position).getDescricao());
        holder.getStatusChamado().setText(StatusHandler.status.get(chamados.get(position).getStatus()));

        return view;
    }

    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
