package br.com.leroymerlin.model;

/**
 * Created by PDA on 01/10/2017.
 */

public class MargemNegativa {

    private float cod;
    private String regional;
    private int codigoFilial;
    private String filial;
    private String codigoProduto;
    private String descricao;
    private String quantidade;
    private String precoVenda;
    private String custo;
    private String encargos;
    private String margem;
    private String dtAtuest;
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

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(String precoVenda) {
        this.precoVenda = precoVenda;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getEncargos() {
        return encargos;
    }

    public void setEncargos(String encargos) {
        this.encargos = encargos;
    }

    public String getMargem() {
        return margem;
    }

    public void setMargem(String margem) {
        this.margem = margem;
    }

    public String getDtAtuest() {
        return dtAtuest;
    }

    public void setDtAtuest(String dtAtuest) {
        this.dtAtuest = dtAtuest;
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
