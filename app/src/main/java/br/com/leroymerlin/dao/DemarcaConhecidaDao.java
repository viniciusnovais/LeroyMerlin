package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.DemarcaConhecida;

/**
 * Created by PDA on 01/10/2017.
 */

public class DemarcaConhecidaDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public DemarcaConhecidaDao(Context context) {
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


    public void incluir(List<DemarcaConhecida> lista) {
        try {
            deletar();
            for (DemarcaConhecida d : lista) {
                ContentValues values = new ContentValues();
                values.put("cod", d.getCod());
                values.put("codigoFilial", d.getCodigoFilial());
                values.put("filial", d.getFilial());
                values.put("numNota", d.getNumNota());
                values.put("serie", d.getSerie());
                values.put("codigoProduto", d.getCodigoProduto());
                values.put("descricao", d.getDescricao());
                values.put("quantidade", d.getQuantidade());
                values.put("valorTotal", d.getValorTotal());
                values.put("dtAtuest", d.getDtAtuest());
                values.put("justificativa", d.getJustificativa());
                values.put("nomeUser", d.getNomeUser());
                values.put("regional", d.getRegional());
                values.put("dataInicio", d.getDataInicio());
                values.put("dataFim", d.getDataFim());
                values.put("export", 0);
                values.put("imagem", d.getImagem());

                getDataBase().insert("demarcaConhecida", null, values);
            }

            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarJustificativa(DemarcaConhecida d) {
        ContentValues values = new ContentValues();
        values.put("justificativa", d.getJustificativa());
        values.put("imagem", d.getImagem());
        getDataBase().update("demarcaConhecida", values, "cod = ?",
                new String[]{d.getCod() + ""});
    }

    public void export(DemarcaConhecida d) {
        ContentValues values = new ContentValues();
        values.put("export", 1);
        getDataBase().update("demarcaConhecida", values, "cod = ?",
                new String[]{d.getCod() + ""});
    }


    public List<DemarcaConhecida> listar(int codFilial) {
        List<DemarcaConhecida> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM demarcaConhecida WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM demarcaConhecida WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }

        try {

            while (cursor.moveToNext()) {
                DemarcaConhecida d = new DemarcaConhecida();
                d.setCod(cursor.getFloat(cursor.getColumnIndex("cod")));
                d.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                d.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                d.setNumNota(cursor.getString(cursor.getColumnIndex("numNota")));
                d.setSerie(cursor.getString(cursor.getColumnIndex("serie")));
                d.setCodigoProduto(cursor.getString(cursor.getColumnIndex("codigoProduto")));
                d.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                d.setQuantidade(cursor.getString(cursor.getColumnIndex("quantidade")));
                d.setValorTotal(cursor.getString(cursor.getColumnIndex("valorTotal")));
                d.setDtAtuest(cursor.getString(cursor.getColumnIndex("dtAtuest")));
                d.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                d.setNomeUser(cursor.getString(cursor.getColumnIndex("nomeUser")));
                d.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                d.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
                d.setDataFim(cursor.getString(cursor.getColumnIndex("dataFim")));
                d.setImagem(cursor.getBlob(cursor.getColumnIndex("imagem")));
                lista.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return lista;

    }


    public void deletar() {
        getDataBase().delete("demarcaConhecida", null, null);

    }


}
