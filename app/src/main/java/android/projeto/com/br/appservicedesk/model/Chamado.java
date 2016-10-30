package android.projeto.com.br.appservicedesk.model;

import android.projeto.com.br.appservicedesk.enums.TiposDeFila;

import java.util.Date;

/**
 * Created by caiquecoelho on 10/1/16.
 */
public class Chamado {

    private int numero;
    private String descricao;
    private Date dataDeFechamento;
    private Date dataAbertura;
    private int status;

    public Chamado(int numero, String descricao, Date dataDeFechamento, Date dataAbertura, int status) {
        this.numero = numero;
        this.descricao = descricao;
        this.dataDeFechamento = dataDeFechamento;
        this.dataAbertura = dataAbertura;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDeFechamento() {
        return dataDeFechamento;
    }

    public void setDataDeFechamento(Date dataDeFechamento) {
        this.dataDeFechamento = dataDeFechamento;
    }

    public Date getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
