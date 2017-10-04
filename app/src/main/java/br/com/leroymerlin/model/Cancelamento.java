package br.com.leroymerlin.model;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by PDA on 22/09/2017.
 */

public class Cancelamento implements KvmSerializable {

    private float cod;
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
    private byte[] imagem;

    public Cancelamento() {
        codigoFilial = 0;
        filial = "";
        regional = "";
        numNota = 0;
        serie = "";
        valorTotal = 0;
        datNota = "";
        dataCancelamento = "";
        numCaixa = 0;
        usuarioCancel = "";
        codigoUsuario = 0;
        codigoCondicao = 0;
        codigoPagto = "";
        dataDe = "";
        dataAte = "";
    }

    public Cancelamento(int codigoFilial, String filial, String regional, float numNota, String serie, float valorTotal, String datNota, String dataCancelament, float numCaixa, String usuarioCancel, int codigoUsuario, int codigoCondicao, String codigoPagto, String dataDe, String dataAte) {
        this.codigoFilial = codigoFilial;
        this.filial = filial;
        this.regional = regional;
        this.numNota = numNota;
        this.serie = serie;
        this.valorTotal = valorTotal;
        this.datNota = datNota;
        this.dataCancelamento = dataCancelamento;
        this.numCaixa = numCaixa;
        this.usuarioCancel = usuarioCancel;
        this.codigoUsuario = codigoUsuario;
        this.codigoCondicao = codigoCondicao;
        this.codigoPagto = codigoPagto;
        this.dataDe = dataDe;
        this.dataAte = dataAte;
    }

    public float getCod() {
        return cod;
    }

    public void setCod(float cod) {
        this.cod = cod;
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
                return regional;
            case 1:
                return codigoFilial;
            case 2:
                return filial;
            case 3:
                return numNota;
            case 4:
                return serie;
            case 5:
                return valorTotal;
            case 6:
                return datNota;
            case 7:
                return dataCancelamento;
            case 8:
                return numCaixa;
            case 9:
                return usuarioCancel;
            case 10:
                return codigoCondicao;
            case 11:
                return codigoPagto;
            case 12:
                return codigoUsuario;
            case 13:
                return dataDe;

            case 14:
                return dataAte;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 15;
    }

    @Override
    public void setProperty(int i, Object o) {

        switch (i) {
            case 0:
                regional = o.toString();
                break;
            case 1:
                codigoFilial = Integer.parseInt(o.toString());
                break;
            case 2:
                filial = o.toString();
                break;
            case 3:
                numNota = Float.parseFloat(o.toString());
                break;
            case 4:
                serie = o.toString();
                break;
            case 5:
                valorTotal = Float.parseFloat(o.toString());
                break;
            case 6:
                datNota = o.toString();
                break;
            case 7:
                dataCancelamento = o.toString();
                break;
            case 8:
                numCaixa = Integer.parseInt(o.toString());
                break;
            case 9:
                usuarioCancel = o.toString();
                break;
            case 10:
                codigoCondicao = Integer.parseInt(o.toString());
                break;
            case 11:
                codigoPagto = o.toString();
                break;
            case 12:
                codigoUsuario = Integer.parseInt(o.toString());
                break;
            case 13:
                dataDe = o.toString();
                break;
            case 14:
                dataAte = o.toString();
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Regional";
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
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "NumNota";
                break;

            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Serie";
                break;

            case 5:
                propertyInfo.type = PropertyInfo.LONG_CLASS;
                propertyInfo.name = "ValorTotal";
                break;

            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataNota";
                break;

            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataCancelamento";
                break;

            case 8:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "NumCaixa";
                break;

            case 9:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "UsuarioCancel";
                break;

            case 10:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CodigoCondicao";
                break;

            case 11:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "CondicaoPagto";
                break;
            case 12:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "CodigoUsuario";
                break;

            case 13:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataDe";
                break;

            case 14:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "DataAte";
                break;
        }
    }
}
