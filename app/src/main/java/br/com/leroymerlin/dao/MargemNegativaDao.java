package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearSmoothScroller;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.DemarcaConhecida;
import br.com.leroymerlin.model.MargemNegativa;

/**
 * Created by PDA on 01/10/2017.
 */

public class MargemNegativaDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public MargemNegativaDao(Context context) {
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


    public void incluir(List<MargemNegativa> lista) {
        try {
            deletar();

            for (MargemNegativa m : lista) {
                ContentValues values = new ContentValues();
                values.put("cod", m.getCod());
                values.put("regional", m.getRegional());
                values.put("codigoFilial", m.getCodigoFilial());
                values.put("filial", m.getFilial());
                values.put("codigoProduto", m.getCodigoProduto());
                values.put("descricao", m.getDescricao());
                values.put("quantidade", m.getQuantidade());
                values.put("precoVenda", m.getPrecoVenda());
                values.put("custo", m.getCusto());
                values.put("encargos", m.getEncargos());
                values.put("margem", m.getMargem());
                values.put("dtAtuest", m.getDtAtuest());
                values.put("dataInicio", m.getDataInicio());
                values.put("dataFim", m.getDataFim());
                values.put("justificativa", m.getJustificativa());
                values.put("export", 0);
                values.put("imagem", m.getImagem());

                getDataBase().insert("margemNegativa", null, values);
            }
            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void alterarJustificativa(MargemNegativa m) {
        ContentValues values = new ContentValues();

        values.put("justificativa", m.getJustificativa());
        values.put("imagem", m.getImagem());

        getDataBase().update("margemNegativa", values, "cod = ?", new String[]{m.getCod() + ""});
    }

    public void export(MargemNegativa m) {

        ContentValues values = new ContentValues();
        values.put("export", 1);
    }

    public List<MargemNegativa> listar(int codFilial) {
        List<MargemNegativa> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM margemNegativa WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM margemNegativa WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }

        try {
            while (cursor.moveToNext()) {
                MargemNegativa m = new MargemNegativa();

                //m.setCod(cursor.getFloat(cursor.getColumnIndex("cod")));
                m.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                m.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                m.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                m.setCodigoProduto(cursor.getString(cursor.getColumnIndex("codigoProduto")));
                m.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                m.setQuantidade(cursor.getString(cursor.getColumnIndex("quantidade")));
                m.setPrecoVenda(cursor.getString(cursor.getColumnIndex("precoVenda")));
                m.setCusto(cursor.getString(cursor.getColumnIndex("custo")));
                m.setEncargos(cursor.getString(cursor.getColumnIndex("encargos")));
                m.setMargem(cursor.getString(cursor.getColumnIndex("margem")));
                m.setDtAtuest(cursor.getString(cursor.getColumnIndex("dtAtuest")));
                m.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
                m.setDataFim(cursor.getString(cursor.getColumnIndex("dataFim")));
                m.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                m.setImagem(cursor.getBlob(cursor.getColumnIndex("imagem")));

                lista.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void deletar() {

        getDataBase().delete("margemNegativa", null, null);
    }

}
