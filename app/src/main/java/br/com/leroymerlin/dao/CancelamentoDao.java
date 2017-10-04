package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 18/09/2017.
 */

public class CancelamentoDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public CancelamentoDao(Context context) {
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


    public void incluir(List<Cancelamento> lista) {

        try {
            deletar();
            for (Cancelamento c : lista) {
                ContentValues values = new ContentValues();
                values.put("cod", c.getCod());
                values.put("codigoFilial", c.getCodigoFilial());
                values.put("filial", c.getFilial());
                values.put("regional", c.getRegional());
                values.put("numNota", c.getNumNota());
                values.put("serie", c.getSerie());
                values.put("valorTotal", c.getValorTotal());
                values.put("dataNota", c.getDatNota());
                values.put("dataCancelamento", c.getDataCancelamento());
                values.put("numCaixa", c.getNumCaixa());
                values.put("usuarioCancel", c.getUsuarioCancel());
                values.put("codigoCondicao", c.getCodigoCondicao());
                values.put("codicaoPagto", c.getCodigoPagto());
                values.put("justificativa", "");
                values.put("codigoUsuario", c.getCodigoUsuario());
                values.put("dataDe", c.getDataDe());
                values.put("dataAte", c.getDataAte());
                values.put("export", 0);
                values.put("imagem", c.getImagem());


                getDataBase().insert("cancelamento", null, values);
            }

            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarJustificativa(Cancelamento c) {
        ContentValues values = new ContentValues();
        values.put("justificativa", c.getJustificativa());
        values.put("imagem", c.getImagem());
        getDataBase().update("cancelamento", values, "cod = ?", new String[]{c.getCod() + ""});
    }

    public void export(Cancelamento c) {
        ContentValues values = new ContentValues();
        values.put("export", 1);
        getDataBase().update("cancelamento", values, "cod = ?",
                new String[]{c.getCod() + ""});
    }


    public List<Cancelamento> listar(int codFilial) {
        List<Cancelamento> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM cancelamento WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM cancelamento WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }

        try {
            while (cursor.moveToNext()) {
                Cancelamento c = new Cancelamento();
                c.setCod(cursor.getFloat(cursor.getColumnIndex("cod")));
                c.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                c.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                c.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                c.setNumNota(cursor.getFloat(cursor.getColumnIndex("numNota")));
                c.setSerie(cursor.getString(cursor.getColumnIndex("serie")));
                c.setValorTotal(cursor.getFloat(cursor.getColumnIndex("valorTotal")));
                c.setDatNota(cursor.getString(cursor.getColumnIndex("dataNota")));
                c.setDataCancelamento(cursor.getString(cursor.getColumnIndex("dataCancelamento")));
                c.setNumCaixa(cursor.getFloat(cursor.getColumnIndex("numCaixa")));
                c.setUsuarioCancel(cursor.getString(cursor.getColumnIndex("usuarioCancel")));
                c.setCodigoCondicao(cursor.getInt(cursor.getColumnIndex("codigoCondicao")));
                c.setCodigoPagto(cursor.getString(cursor.getColumnIndex("codicaoPagto")));
                c.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                c.setCodigoUsuario(cursor.getInt(cursor.getColumnIndex("codigoUsuario")));
                c.setDataAte(cursor.getString(cursor.getColumnIndex("dataAte")));
                c.setDataDe(cursor.getString(cursor.getColumnIndex("dataDe")));

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lista;
    }

    public void deletar() {
        getDataBase().delete("cancelamento", null, null);
    }
}
