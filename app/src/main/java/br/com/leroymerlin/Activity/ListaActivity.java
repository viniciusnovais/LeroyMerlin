package br.com.leroymerlin.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.leroymerlin.Adapter.ListaJustificativaCancelamentoAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaDescontoAdapter;
import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapCancelamento;
import br.com.leroymerlin.WebService.WebServiceSoapDesconto;
import br.com.leroymerlin.dao.CancelamentoDao;
import br.com.leroymerlin.dao.DescontoDao;
import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 05/09/2017.
 */

public class ListaActivity extends AppCompatActivity {

    private List<Desconto> lista;
    private List<Cancelamento> cancelamentoList;
    private RecyclerView recyclerView;
    private Desconto d = new Desconto();
    private int tipo;
    private String justificativa = "";
    private ListaJustificativaDescontoAdapter adapter;
    private ListaJustificativaCancelamentoAdapter cancelamentoAdapter;
    private DescontoDao descontoDao;
    private CancelamentoDao cancelamentoDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        descontoDao = new DescontoDao(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        tipo = getIntent().getIntExtra("tipo", -1);

        AsyncLista task = new AsyncLista();
        task.execute();


    }

    public class AsyncLista extends AsyncTask {

        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);

        @Override
        protected Object doInBackground(Object[] objects) {
            if (tipo == 101) {
                lista = WebServiceSoapDesconto.GetListaDesconto(preferences.getInt("codFilial", -1));
                descontoDao.incluir(lista);
            } else if (tipo == 107) {
                cancelamentoList = WebServiceSoapCancelamento.GetListaCancelamento(preferences.getInt("codFilial", -1));
                cancelamentoDao.incluir(cancelamentoList);

            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            if (tipo == 101) {
                adapter = new ListaJustificativaDescontoAdapter(ListaActivity.this, descontoDao.listar(preferences.getInt("codFilial", -1)));
                recyclerView.setAdapter(adapter);

                adapter.setOnItemClick(new ListaJustificativaDescontoAdapter.ItemOnClick() {
                    @Override
                    public void onClick(int position) {
                        popupJustificar(position);
                    }
                });
            } else if (tipo == 107) {
                cancelamentoAdapter = new ListaJustificativaCancelamentoAdapter(ListaActivity.this, cancelamentoDao.listar(preferences.getInt("codFilial", -1)));
                recyclerView.setAdapter(cancelamentoAdapter);

                cancelamentoAdapter.setOnItemClick(new ListaJustificativaCancelamentoAdapter.ItemOnClick() {
                    @Override
                    public void onClick(int position) {
                        popupJustificar(position);
                    }
                });
            }
        }
    }

    public class AsyncPostJustificativa extends AsyncTask<Object, Void, Object[]> {


        @Override
        protected Object[] doInBackground(Object... params) {

            boolean resposta = WebServiceSoapDesconto.postJustificativa((Desconto) params[0]);

            //se exportar a resposta, muda a flag para 1
            if (resposta) {
                params[2] = resposta;
                return params;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object[] objects) {
            super.onPostExecute(objects);

            if (objects != null) {
                if ((boolean) objects[2]) {
                    descontoDao.export((Desconto) objects[0]);
                    //atualizando lista
                    adapter.remove(Integer.parseInt(objects[1].toString()));
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_botoes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.btGrafico:
                Intent i = new Intent(ListaActivity.this, GraficoActivity.class);
                i.putExtra("tipo", tipo);
                startActivity(i);
                finish();
                return true;
            case R.id.btLista:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void popupJustificar(final int position) {
        final AlertDialog dialog;
        View v = View.inflate(ListaActivity.this, R.layout.popup_justificativa, null);
        final EditText editJustificar = (EditText) v.findViewById(R.id.editJustificativa);
        Button btEnviar = (Button) v.findViewById(R.id.btEnviarJustificativa);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
        builder.setView(v);
        dialog = builder.create();

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editJustificar.getEditableText().toString().equals("")) {
                    justificativa = editJustificar.getText().toString();
                    d = lista.get(position);
                    d.setJustificativa(justificativa);
                    descontoDao.alterarJustificativa(d);

                    AsyncPostJustificativa task = new AsyncPostJustificativa();
                    task.execute(d, position, false);

                    dialog.dismiss();

                }
            }
        });

        dialog.show();

    }

}
