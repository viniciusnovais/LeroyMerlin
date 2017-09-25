package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Grafico;

/**
 * Created by PDA on 11/09/2017.
 */

public class GraficoDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public GraficoDao(Context context) {
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


    public void incluir(List<Grafico> lista) {

        try {
            deletar();
            for (Grafico g : lista) {
                ContentValues values = new ContentValues();

                values.put("descricao", g.getDescricao());
                values.put("valor", g.getValor());
                values.put("ano", g.getAno());
                values.put("mes", g.getMes());
                values.put("semana", g.getSemana());
                values.put("dia", g.getDia());
                values.put("tipoGrafico", g.getTipoGrafico());

                getDataBase().insert("grafico", null, values);
            }

            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Grafico> listar() {
        List<Grafico> lista = new ArrayList<>();
        Cursor cursor = getDataBase().rawQuery("SELECT * FROM grafico", null);

        try {


            while (cursor.moveToNext()) {
                Grafico g = new Grafico();

                g.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
                g.setValor(cursor.getInt(cursor.getColumnIndex("valor")));
                g.setTipoGrafico(cursor.getInt(cursor.getColumnIndex("tipoGrafico")));
                lista.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lista;
    }

    public List<Grafico> listarResumo() {
        List<Grafico> lista = new ArrayList<>();
        Cursor cursor = getDataBase().rawQuery("SELECT * FROM grafico WHERE tipoGrafico = 4", null);

        try {
            while (cursor.moveToNext()) {
                Grafico g = new Grafico();

                g.setSemana(cursor.getInt(cursor.getColumnIndex("semana")));
                g.setDia(cursor.getInt(cursor.getColumnIndex("dia")));
                g.setMes(cursor.getInt(cursor.getColumnIndex("mes")));
                g.setAno(cursor.getInt(cursor.getColumnIndex("ano")));
                g.setTipoGrafico(cursor.getInt(cursor.getColumnIndex("tipoGrafico")));
                lista.add(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return lista;
    }


    public void deletar() {
        getDataBase().delete("grafico", null, null);
    }
}
