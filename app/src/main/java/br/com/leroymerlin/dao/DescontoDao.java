package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 18/09/2017.
 */

public class DescontoDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public DescontoDao(Context context) {
        helper = new DataBaseHelper(context);
    }

    public SQLiteDatabase getDataBase() {
        if (database == null || !database.isOpen()) {
            database = helper.getWritableDatabase();
        }

        return database;
    }

    public void close() {
        helper.close();
        if (database != null && database.isOpen()) {
            database.close();
        }
    }


    public void incluir(List<Desconto> lista) {

        try {
            deletar();
            for (Desconto d : lista) {
                ContentValues values = new ContentValues();

                values.put("codigoUsuario", d.getCodigoUsuario());
                values.put("codigoFilial", d.getCodigoFilial());
                values.put("filial", d.getFilial());
                values.put("regional", d.getRegional());
                values.put("numNota", d.getNumNota());
                values.put("serie", d.getSerie());
                values.put("secao", d.getSecao());
                values.put("codLm", d.getCodLm());
                values.put("descricao", d.getDescricao());
                values.put("valorNF", d.getValorNF());
                values.put("valorDesconto", d.getValorDesconto());
                values.put("percentual", d.getPercentual());
                values.put("ocorrencia", d.getOcorrencia());
                values.put("data", d.getData());
                values.put("justificativa", d.getJustificativa());
                values.put("dataInicio", d.getDataInicio());
                values.put("dataFim", d.getDataFim());
                values.put("nomeCliente", d.getNomeCliente());
                values.put("cpfCliente", d.getCpfCliente());
                values.put("export", 0);

                getDataBase().insert("desconto", null, values);
            }

            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarJustificativa(Desconto d) {
        ContentValues values = new ContentValues();
        values.put("justificativa", d.getJustificativa());

        getDataBase().update("desconto", values, "numNota = ? and serie = ? and codigoFilial = ? and codLm = ?",
                new String[]{d.getNumNota() + "", d.getSerie(), d.getCodigoFilial() + "", d.getCodLm() + ""});
    }

    public void export(Desconto d) {
        ContentValues values = new ContentValues();
        values.put("export", 1);
        getDataBase().update("desconto", values, "numNota = ? and serie = ? and codigoFilial = ? and codLm = ?",
                new String[]{d.getNumNota() + "", d.getSerie(), d.getCodigoFilial() + "", d.getCodLm() + ""});
    }


    public List<Desconto> listar(int codFilial) {
        List<Desconto> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM desconto WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM desconto WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }

        try {
            while (cursor.moveToNext()) {
                Desconto d = new Desconto();
                d.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("codigoUsuario")));
                d.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                d.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                d.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                d.setNumNota(cursor.getFloat(cursor.getColumnIndex("numNota")));
                d.setSerie(cursor.getString(cursor.getColumnIndex("serie")));
                d.setSecao(cursor.getString(cursor.getColumnIndex("secao")));
                d.setCodLm(cursor.getFloat(cursor.getColumnIndex("codLm")));
                d.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                d.setValorNF(cursor.getFloat(cursor.getColumnIndex("valorNF")));
                d.setValorDesconto(cursor.getFloat(cursor.getColumnIndex("valorDesconto")));
                d.setPercentual(cursor.getFloat(cursor.getColumnIndex("percentual")));
                d.setOcorrencia(cursor.getString(cursor.getColumnIndex("ocorrencia")));
                d.setData(cursor.getString(cursor.getColumnIndex("data")));
                d.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                d.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
                d.setDataFim(cursor.getString(cursor.getColumnIndex("dataFim")));
                d.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
                d.setCpfCliente(cursor.getString(cursor.getColumnIndex("cpfCliente")));

                lista.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lista;
    }

    public void deletar() {
        getDataBase().delete("desconto", null, null);
    }
}
