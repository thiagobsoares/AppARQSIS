package android.projeto.com.br.appservicedesk.model;


import android.widget.TextView;

/**
 * Created by caiquecoelho on 10/14/16..
 */
public class ViewHolder {


    private TextView descricaoChamado, numeroChamado,statusChamado;

    public ViewHolder(TextView descricaoChamado, TextView numeroChamado, TextView statusChamado) {
        this.descricaoChamado = descricaoChamado;
        this.numeroChamado = numeroChamado;
        this.statusChamado = statusChamado;
    }

    public TextView getDescricaoChamado() {
        return descricaoChamado;
    }

    public void setDescricaoChamado(TextView descricaoChamado) {
        this.descricaoChamado = descricaoChamado;
    }

    public TextView getNumeroChamado() {
        return numeroChamado;
    }

    public void setNumeroChamado(TextView numeroChamado) {
        this.numeroChamado = numeroChamado;
    }

    public TextView getStatusChamado() {
        return statusChamado;
    }

    public void setStatusChamado(TextView statusChamado) {
        this.statusChamado = statusChamado;
    }
}

