package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGet;

import static br.com.leroymerlin.Activity.LoginActivity.PREF_NAME;

/**
 * Created by PDA on 30/08/2017.
 */

public class AtendimentoGrupoActivity extends AppCompatActivity {

    private Button btDesconto, btCancelamentoCupom, btPedidoPendente;
    private Bundle bundle;
    static boolean errored;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_atendimento);

        btDesconto = (Button) findViewById(R.id.btDesconto);
        btCancelamentoCupom = (Button) findViewById(R.id.btCancelamentoCupom);
        btPedidoPendente = (Button) findViewById(R.id.btPedidoPendente);

        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        Toast.makeText(this, "cod" + preferences.getInt("codFilial", 0), Toast.LENGTH_SHORT).show();


        //pegando as quantidades do serviço q estavam na outra activity
        bundle = getIntent().getBundleExtra("bundle_atendimento");

        btDesconto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir para tela dos Gráficos
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                i.putExtra("tipo", 101);
                startActivity(i);
            }
        });

        btCancelamentoCupom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                i.putExtra("tipo", 107);
                startActivity(i);
            }
        });

        btPedidoPendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                i.putExtra("tipo", 111);
                startActivity(i);
            }
        });

        AsyncEvent task = new AsyncEvent();
        task.execute();
    }


    public class AsyncEvent extends AsyncTask {

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SoapObject response = null;

        @Override
        protected Object doInBackground(Object[] params) {
            response = WebServiceSoapGet.EventoAberto(sharedPreferences.getInt("codUsuario", 0), bundle.getInt("bundle_atendimento"));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (!errored) {
                //Pegando resposta Pai
                List<SoapObject> subResponse = new ArrayList<>();
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    subResponse.add((SoapObject) response.getProperty(i));
                }
                if (subResponse != null) {
                    //SubResposta, Filho
                    for (SoapObject obj : subResponse) {
                        int cod = Integer.parseInt(obj.getProperty("CodigoMenu").toString());
                        int quantidade = Integer.parseInt(obj.getProperty("Pendencia").toString());

                        //só deixo para saber qual codigo do subgrupo estou usando
                        Integer.parseInt(obj.getProperty("CodigoTile").toString());

                        if (cod == 141) {
                            if (quantidade >= 0) {
                                btDesconto.setTextColor(Color.RED);
                                btDesconto.setText(quantidade + "");
                            }
                        }

                        if (cod == 144) {
                            if (quantidade >= 0) {
                                btCancelamentoCupom.setTextColor(Color.RED);
                                btCancelamentoCupom.setText(quantidade + "");
                            }
                        }

                        if (cod == 148) {
                            if (quantidade >= 0) {
                                btPedidoPendente.setTextColor(Color.RED);
                                btPedidoPendente.setText(quantidade + "");
                            }
                        }
                    }
                }
            }
        }
    }
}
