package android.projeto.com.br.appservicedesk.model;


import java.util.Date;

/**
 * Created by caiquecoelho on 10/1/16.
 */
public class Chamado {

    private long numero;
    private String descricao;
    private Date dataDeFechamento;
    private Date dataAbertura;
    private int status;
    private int fila;

    public Chamado(long numero, String descricao, Date dataDeFechamento, Date dataAbertura, int status,int fila) {
        this.numero = numero;
        this.descricao = descricao;
        this.dataDeFechamento = dataDeFechamento;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.fila = fila;
    }

    public Chamado(String descricao,int status,int fila) {
        this.descricao = descricao;
        this.dataAbertura = new Date();
        this.status = status;
        this.fila = fila;
    }


    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }


    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
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
