package br.com.leroymerlin.model;

/**
 * Created by PDA on 01/10/2017.
 */

public class FaturamentoPendente {

    private float cod;
    private String regional;
    private int codigoFilial;
    private String filial;
    private String pedido;
    private String nomeCliente;
    private String dataPedido;
    private String dataEntrega;
    private String valorTotal;
    private String valorParcial;
    private int numCaixa;
    private String dataInicio;
    private String dataFim;
    private String justificativa;
    private int export;
    private byte[] imagem;

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

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

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(String valorParcial) {
        this.valorParcial = valorParcial;
    }

    public int getNumCaixa() {
        return numCaixa;
    }

    public void setNumCaixa(int numCaixa) {
        this.numCaixa = numCaixa;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public float getCod() {
        return cod;
    }

    public void setCod(float cod) {
        this.cod = cod;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}
