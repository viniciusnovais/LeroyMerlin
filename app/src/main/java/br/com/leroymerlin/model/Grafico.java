package br.com.leroymerlin.model;

/**
 * Created by PDA on 08/09/2017.
 */

public class Grafico {

    //atributos somente pro gráfico
    private String descricao;
    private int valor;

    private int tipoGrafico;

    //atributos somente pro resumo
    private int dia;
    private int mes;
    private int ano;
    private int semana;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getTipoGrafico() {
        return tipoGrafico;
    }

    public void setTipoGrafico(int tipoGrafico) {
        this.tipoGrafico = tipoGrafico;
    }
}
