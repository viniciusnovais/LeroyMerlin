package br.com.leroymerlin.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.leroymerlin.Adapter.PageAdapterGrafico;
import br.com.leroymerlin.model.Grafico;
import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGetInfoGrafico;
import br.com.leroymerlin.dao.GraficoDao;

/**
 * Created by PDA on 31/08/2017.
 */

public class GraficoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tvHoje, tvMes, tvSemana, tvAno;
    private GraficoDao graficoDao;
    private int codFilial, tipo;
    private ProgressDialog progressDialog;
    private String[] titulos = new String[]{"H/ por Filial(R$)", "H/ por Região(R$)", "No mês por dia(R$)", "últimos meses(R$)",};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvAno = (TextView) findViewById(R.id.tvAno);
        tvMes = (TextView) findViewById(R.id.tvMes);
        tvHoje = (TextView) findViewById(R.id.tvHoje);
        tvSemana = (TextView) findViewById(R.id.tvSemana);

        codFilial = preferences.getInt("codFilial", -1);
        tipo = getIntent().getIntExtra("tipo", -1);
        graficoDao = new GraficoDao(this);

        AsynGrafico task = new AsynGrafico();
        task.execute(tipo, codFilial);
    }


    @Override
    protected void onResume() {
        super.onResume();


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
                return true;
            case R.id.btLista:
                Intent i = new Intent(GraficoActivity.this, ListaActivity.class);
                i.putExtra("tipo", tipo);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class AsynGrafico extends AsyncTask<Integer, Object, List<Grafico>> {

        public List<Grafico> lista;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(GraficoActivity.this);
            progressDialog.setMessage("Carregando...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }

        @Override
        protected List<Grafico> doInBackground(Integer... params) {
            //depois alterar para params[0] onde irá receber o codigo do tipo de serviço

            lista = WebServiceSoapGetInfoGrafico.infoGrafico(params[0], params[1]);
            graficoDao.incluir(lista);

            return graficoDao.listarResumo();
        }

        @Override
        protected void onPostExecute(List<Grafico> grafico) {
            super.onPostExecute(grafico);

            if (progressDialog.isShowing()) {
                progressDialog.dismiss();

                while (graficoDao.FINISH) {
                    try {
                        DecimalFormat df = new DecimalFormat("##,###,###,###");
                        tvAno.setText(df.format(graficoDao.listarResumo().get(0).getAno()).replace(",", "."));
                        tvMes.setText(df.format(graficoDao.listarResumo().get(0).getMes()).replace(",", "."));
                        tvSemana.setText(df.format(graficoDao.listarResumo().get(0).getSemana()).replace(",", "."));
                        tvHoje.setText(df.format(graficoDao.listarResumo().get(0).getDia()).replace(",", "."));

                        viewPager.setAdapter(new PageAdapterGrafico(getSupportFragmentManager(), titulos, codFilial));
                        tabLayout.setupWithViewPager(viewPager);

                        graficoDao.FINISH = false;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
