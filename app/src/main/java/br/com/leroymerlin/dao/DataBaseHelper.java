package br.com.leroymerlin.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PDA on 11/09/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSAO = 11;
    private static final String BANCO_DADOS = "LeroyMerlin";

    public DataBaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS grafico(descricao TEXT, valor REAL, ano INTEGER, mes INTEGER, dia INTEGER, semana INTEGER, tipoGrafico INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS desconto(cod REAL,codigoUsuario INTEGER, codigoFilial INTEGER, filial TEXT, regional TEXT, numNota REAL, serie TEXT," +
                " secao TEXT, codLm TEXT, descricao TEXT, valorNF REAL, valorDesconto REAL, percentual REAL, ocorrencia TEXT, data TEXT," +
                " justificativa TEXT, dataInicio TEXT, dataFim TEXT, nomeCliente TEXT, cpfCliente TEXT,valorVenda REAL, operador TEXT, vendedor TEXT, export INTEGER," +
                " imagem BLOB)");

        db.execSQL("CREATE TABLE IF NOT EXISTS cancelamento(cod REAL, codigoFilial INTEGER, filial TEXT, regional TEXT, numNota REAL, serie TEXT," +
                " valorTotal REAL, dataNota TEXT, dataCancelamento TEXT, numCaixa INTEGER, usuarioCancel TEXT, codigoCondicao INTEGER, codicaoPagto TEXT, justificativa TEXT," +
                " codigoUsuario INTEGER, dataDe TEXT, dataAte TEXT,export INTEGER, imagem BLOB)");

        db.execSQL("CREATE TABLE IF NOT EXISTS assistenciaTecnica(cod REAL, regional TEXT, codigoFilial INTEGER, filial TEXT, numNota TEXT, serie TEXT, nomeCliente TEXT, valorTotal TEXT," +
                " dataNota TEXT, dataInicio TEXT, dataFim TEXT, justificativa TEXT, export INTEGER, imagem BLOB)");

        db.execSQL("CREATE TABLE IF NOT EXISTS demarcaConhecida(cod REAL, codigoFilial INTEGER, filial TEXT, numNota TEXT, serie TEXT, codigoProduto TEXT, descricao TEXT, quantidade TEXT," +
                " valorTotal TEXT, dtAtuest TEXT, justificativa TEXT, nomeUser TEXT, regional TEXT, dataInicio TEXT, dataFim TEXT, export INTEGER, imagem BLOB)");

        db.execSQL("CREATE TABLE IF NOT EXISTS pendenteFaturamento(cod REAL,filial TEXT,regional TEXT, codigoFilial INTEGER, pedido TEXT, nomeCliente TEXT, dataPedido TEXT, dataEntrega TEXT, valorTotal TEXT," +
                " valorParcial TEXT, numCaixa INTEGER, dataInicio TEXT, dataFim TEXT, justificativa TEXT, export INTEGER, imagem BLOB)");

        db.execSQL("CREATE TABLE IF NOT EXISTS margemNegativa(cod REAL, regional TEXT, codigoFilial INTEGER, filial TEXT, codigoProduto TEXT, descricao TEXT, quantidade TEXT, precoVenda TEXT," +
                " custo TEXT, encargos TEXT, margem TEXT, dtAtuest, dataInicio TEXT, dataFim TEXT, justificativa TEXT, export INTEGER, imagem BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion != newVersion) {
            deleteDataBases(db);
            onCreate(db);
        }
    }

    private void deleteDataBases(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        List<String> tables = new ArrayList<>();

        while (cursor.moveToNext()) {
            if (!cursor.getString(0).equals("sqlite_sequence")) {
                tables.add(cursor.getString(0));
            }
        }

        for (String table : tables) {
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            db.execSQL(dropQuery);
        }
    }
}
