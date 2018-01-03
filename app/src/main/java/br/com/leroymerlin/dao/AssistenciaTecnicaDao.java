package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.AssistenciaTecnica;

/**
 * Created by PDA on 01/10/2017.
 */

public class AssistenciaTecnicaDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public AssistenciaTecnicaDao(Context context) {
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

    public void incluir(List<AssistenciaTecnica> lista) {

        try {
            deletar();
            for (AssistenciaTecnica a : lista) {
                ContentValues values = new ContentValues();
                values.put("cod", a.getCod());
                values.put("regional", a.getRegional());
                values.put("codigoFilial", a.getCodigoFilial());
                values.put("filial", a.getFilial());
                values.put("numNota", a.getNumNota());
                values.put("serie", a.getSerie());
                values.put("nomeCliente", a.getNomeCliente());
                values.put("valorTotal", a.getValorTotal());
                values.put("dataNota", a.getDataNota());
                values.put("dataInicio", a.getDataInicio());
                values.put("dataFim", a.getDataFim());
                values.put("justificativa", a.getJustificativa());
                values.put("export", 0);
                values.put("imagem", a.getImagem());

                getDataBase().insert("assistenciaTecnica", null, values);

            }

            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarJustificativa(AssistenciaTecnica a) {
        ContentValues values = new ContentValues();
        values.put("justificativa", a.getJustificativa());
        values.put("imagem", a.getImagem());

        getDataBase().update("assistenciaTecnica", values, "cod = ?", new String[]{a.getCod() + ""});
    }

    public void export(AssistenciaTecnica a) {
        ContentValues values = new ContentValues();
        values.put("export", 1);

        getDataBase().update("assistenciaTecnica", values, "cod = ?", new String[]{a.getCod() + ""});
    }

    public List<AssistenciaTecnica> listar(int codFilial) {
        List<AssistenciaTecnica> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM assistenciaTecnica WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM assistenciaTecnica WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }
        try {
            while (cursor.moveToNext()) {
                AssistenciaTecnica a = new AssistenciaTecnica();
                a.setCod(cursor.getFloat(cursor.getColumnIndex("cod")));
                a.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                a.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                a.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                a.setNumNota(cursor.getString(cursor.getColumnIndex("numNota")));
                a.setSerie(cursor.getString(cursor.getColumnIndex("serie")));
                a.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
                a.setValorTotal(cursor.getString(cursor.getColumnIndex("valorTotal")));
                a.setDataNota(cursor.getString(cursor.getColumnIndex("dataNota")));
                a.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
                a.setDataFim(cursor.getString(cursor.getColumnIndex("dataFim")));
                a.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                a.setImagem(cursor.getBlob(cursor.getColumnIndex("imagem")));
                lista.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return lista;
    }


    public void deletar() {
        getDataBase().delete("assistenciaTecnica", null, null);
    }

}
