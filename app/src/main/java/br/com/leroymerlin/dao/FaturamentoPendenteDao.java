package br.com.leroymerlin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.FaturamentoPendente;

/**
 * Created by PDA on 02/10/2017.
 */

public class FaturamentoPendenteDao {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    public static boolean FINISH = false;

    public FaturamentoPendenteDao(Context context) {
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


    public void incluir(List<FaturamentoPendente> lista) {

        try {
            deletar();

            for (FaturamentoPendente f : lista) {
                ContentValues values = new ContentValues();
                values.put("cod", f.getCod());
                values.put("regional", f.getRegional());
                values.put("filial", f.getFilial());
                values.put("codigoFilial", f.getCodigoFilial());
                values.put("pedido", f.getPedido());
                values.put("nomeCliente", f.getNomeCliente());
                values.put("dataPedido", f.getDataPedido());
                values.put("dataEntrega", f.getDataEntrega());
                values.put("valorTotal", f.getValorTotal());
                values.put("valorParcial", f.getValorParcial());
                values.put("numCaixa", f.getNumCaixa());
                values.put("dataInicio", f.getDataInicio());
                values.put("dataFim", f.getDataFim());
                values.put("justificativa", f.getJustificativa());
                values.put("export", 0);
                values.put("imagem", f.getImagem());

                getDataBase().insert("pendenteFaturamento", null, values);

            }
            FINISH = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void alterarJustificativa(FaturamentoPendente f) {
        ContentValues values = new ContentValues();
        values.put("justificativa", f.getJustificativa());
        values.put("imagem", f.getImagem());
        getDataBase().update("pendenteFaturamento", values, "cod = ?", new String[]{f.getCod() + ""});
    }

    public void export(FaturamentoPendente f) {
        ContentValues values = new ContentValues();
        values.put("export", 1);
        getDataBase().update("pendenteFaturamento", values, "cod = ?", new String[]{f.getCod() + ""});

    }


    public List<FaturamentoPendente> listar(int codFilial) {

        List<FaturamentoPendente> lista = new ArrayList<>();
        Cursor cursor;
        if (codFilial == -1) {
            cursor = getDataBase().rawQuery("SELECT * FROM pendenteFaturamento WHERE export = 0", null);
        } else {
            cursor = getDataBase().rawQuery("SELECT * FROM pendenteFaturamento WHERE codigoFilial = ? and export = 0", new String[]{codFilial + ""});
        }

        try {
            while (cursor.moveToNext()) {
                FaturamentoPendente f = new FaturamentoPendente();
                f.setCod(cursor.getFloat(cursor.getColumnIndex("cod")));
                f.setFilial(cursor.getString(cursor.getColumnIndex("filial")));
                f.setRegional(cursor.getString(cursor.getColumnIndex("regional")));
                f.setCodigoFilial(cursor.getInt(cursor.getColumnIndex("codigoFilial")));
                f.setPedido(cursor.getString(cursor.getColumnIndex("pedido")));
                f.setNomeCliente(cursor.getString(cursor.getColumnIndex("nomeCliente")));
                f.setDataPedido(cursor.getString(cursor.getColumnIndex("dataPedido")));
                f.setDataEntrega(cursor.getString(cursor.getColumnIndex("dataEntrega")));
                f.setValorTotal(cursor.getString(cursor.getColumnIndex("valorTotal")));
                f.setValorParcial(cursor.getString(cursor.getColumnIndex("valorParcial")));
                f.setNumCaixa(cursor.getInt(cursor.getColumnIndex("numCaixa")));
                f.setDataInicio(cursor.getString(cursor.getColumnIndex("dataInicio")));
                f.setDataFim(cursor.getString(cursor.getColumnIndex("dataFim")));
                f.setJustificativa(cursor.getString(cursor.getColumnIndex("justificativa")));
                f.setImagem(cursor.getBlob(cursor.getColumnIndex("imagem")));
                lista.add(f);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }


    public void deletar() {
        getDataBase().delete("pendenteFaturamento", null, null);
    }
}
