package br.com.leroymerlin.model;

/**
 * Created by PDA on 22/09/2017.
 */

public class Cancelamento {

    private int codigoFilial;
    private String filial;
    private String regional;
    private float numNota;
    private String serie;
    private float valorTotal;
    private String datNota;
    private String dataCancelamento;
    private String justificativa;
    private float numCaixa;
    private String usuarioCancel;
    private int codigoUsuario;
    private int codigoCondicao;
    private String codigoPagto;
    private String dataDe;
    private String dataAte;

    private int export;

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public float getNumNota() {
        return numNota;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public void setNumNota(float numNota) {
        this.numNota = numNota;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }


    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getDatNota() {
        return datNota;
    }

    public void setDatNota(String datNota) {
        this.datNota = datNota;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public float getNumCaixa() {
        return numCaixa;
    }

    public void setNumCaixa(float numCaixa) {
        this.numCaixa = numCaixa;
    }


    public String getUsuarioCancel() {
        return usuarioCancel;
    }

    public void setUsuarioCancel(String usuarioCancel) {
        this.usuarioCancel = usuarioCancel;
    }

    public int getCodigoCondicao() {
        return codigoCondicao;
    }

    public void setCodigoCondicao(int codigoCondicao) {
        this.codigoCondicao = codigoCondicao;
    }

    public String getCodigoPagto() {
        return codigoPagto;
    }

    public void setCodigoPagto(String codigoPagto) {
        this.codigoPagto = codigoPagto;
    }

    public String getDataDe() {
        return dataDe;
    }

    public void setDataDe(String dataDe) {
        this.dataDe = dataDe;
    }

    public String getDataAte() {
        return dataAte;
    }

    public void setDataAte(String dataAte) {
        this.dataAte = dataAte;
    }
}
