package br.com.leroymerlin.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by PDA on 18/09/2017.
 */

public class Desconto implements KvmSerializable {

    private float cod;
    private int codigoUsuario;
    private int codigoFilial;
    private String filial;
    private String regional;
    private String numNota;
    private String serie;
    private String secao;
    private String codLm;
    private String descricao;
    private float valorNF;
    private float valorDesconto;
    private float percentual;
    private String ocorrencia;
    private String data;
    private String justificativa;
    private String dataInicio;
    private String dataFim;
    private String nomeCliente;
    private String cpfCliente;
    private float valorVenda;
    private String operador;
    private String vendedor;
    private int export;
    private byte[] imagem;

    public Desconto() {
        codigoUsuario = 0;
        codigoFilial = 0;
        filial = "";
        regional = "";
        numNota = "";
        serie = "";
        secao = "";
        codLm = "";
        descricao = "";
        valorNF = 0;
        valorDesconto = 0;
        valorVenda = 0;
        percentual = 0;
        ocorrencia = "";
        data = "";
        justificativa = "";
        dataInicio = "";
        dataFim = "";
        nomeCliente = "";
        cpfCliente = "";
        operador = "";
        vendedor = "";
    }

    public Desconto(int codigoUsuario, int codigoFilial, String filial, String regional, String numNota, String serie, String secao, String codLm, String descricao, float valorNF, float valorDesconto, float percentual, String ocorrencia, String data, String justificativa, String dataInicio, String dataFim, String nomeCliente, String cpfCliente, float valorVenda, String operador, String vendedor) {
        this.codigoUsuario = codigoUsuario;
        this.codigoFilial = codigoFilial;
        this.filial = filial;
        this.regional = regional;
        this.numNota = numNota;
        this.serie = serie;
        this.secao = secao;
        this.codLm = codLm;
        this.descricao = descricao;
        this.valorNF = valorNF;
        this.valorDesconto = valorDesconto;
        this.valorVenda = valorVenda;
        this.percentual = percentual;
        this.ocorrencia = ocorrencia;
        this.data = data;
        this.justificativa = justificativa;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.operador = operador;
        this.vendedor = vendedor;

    }

    public float getCod() {
        return cod;
    }

    public void setCod(float cod) {
        this.cod = cod;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
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

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getNumNota() {
        return numNota;
    }

    public void setNumNota(String numNota) {
        this.numNota = numNota;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSecao() {
        return secao;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public String getCodLm() {
        return codLm;
    }

    public void setCodLm(String codLm) {
        this.codLm = codLm;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValorNF() {
        return valorNF;
    }

    public void setValorNF(float valorNF) {
        this.valorNF = valorNF;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public float getPercentual() {
        return percentual;
    }

    public void setPercentual(float percentual) {
        this.percentual = percentual;
    }

    public String getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(String ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
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

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0:
                return codigoUsuario;
            case 1:
                return codigoFilial;
            case 2:
                return filial;
            case 3:
                return regional;
            case 4:
                return numNota;
            case 5:
                return serie;
            case 6:
                return secao;
            case 7:
                return codLm;
            case 8:
                return descricao;
            case 9:
                return valorNF;
            case 10:
                return valorDesconto;
            case 11:
                return valorVenda;
            case 12:
                return percentual;
            case 13:
                return ocorrencia;
            case 14:
                return data;
            case 15:
                return justificativa;
            case 16:
                return dataInicio;
            case 17:
                return dataFim;
            case 18:
                return nomeCliente;
            case 19:
                return cpfCliente;
            case 20:
                return operador;
            case 21:
                return vendedor;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 22;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0:
                codigoUsuario = Integer.parseInt(o.toString());
                break;
            case 1:
                codigoFilial = Integer.parseInt(o.toString());
                break;
            case 2:
                filial = o.toString();
                break;
            case 3:
                regional = o.toString();
                break;
            case 4:
                numNota = o.toString();
                break;
            case 5:
                serie = o.toString();
                break;
            case 6:
                secao = o.toString();
                break;
            case 7:
                codLm = o.toString();
                break;
            case 8:
                descricao = o.toString();
                break;
            case 9:
                valorNF = Float.parseFloat(o.toString());
                break;
            case 10:
                valorDesconto = Float.parseFloat(o.toString());
                break;
            case 11:
                valorVenda = Float.parseFloat(o.toString());
                break;
            case 12:
                percentual = Float.parseFloat(o.toString());
                break;
            case 13:
                ocorrencia = o.toString();
                break;
            case 14:
                data = o.toString();
                break;
            case 15:
                justificativa = o.toString();
                break;
            case 16:
                dataInicio = o.toString();
                break;
            case 17:
                dataFim = o.toString();
                break;
            case 18:
                nomeCliente = o.toString();
                break;
            case 19:
                cpfCliente = o.toString();
                break;
            case 20:
                operador = o.toString();
                break;
            case 21:
                vendedor = o.toString();
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CodigoUsuario";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CodigoFilial";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Filial";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Regional";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "NumNota";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Serie";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Secao";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "CodigoLm";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Descricao";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "ValorNF";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "ValorDesconto";
                break;
            case 11:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "ValorVenda";
                break;
            case 12:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "Percentual";
                break;
            case 13:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Ocorrencia";
                break;
            case 14:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DtAtuest";
                break;
            case 15:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Justificativa";
                break;
            case 16:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataInicio";
                break;
            case 17:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataFim";
                break;
            case 18:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "NomeCliente";
                break;
            case 19:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "CPFCliente";
                break;
            case 20:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Operador";
                break;
            case 21:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Vendedor";
                break;


        }
    }
}
