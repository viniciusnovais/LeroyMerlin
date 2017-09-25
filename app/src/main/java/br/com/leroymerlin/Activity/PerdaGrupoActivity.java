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

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGet;

/**
 * Created by PDA on 31/08/2017.
 */

public class PerdaGrupoActivity extends AppCompatActivity {

    private Button btDemarcaConhecida;
    private Bundle bundle;
    static boolean errored;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_prevencao);

        btDemarcaConhecida = (Button) findViewById(R.id.btDemarcaConhecida);

        bundle = getIntent().getBundleExtra("bundle_perda");

        btDemarcaConhecida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerdaGrupoActivity.this, GraficoActivity.class);
                i.putExtra("tipo", 601);
                startActivity(i);
            }
        });

        AsynEvent task = new AsynEvent();
        task.execute();

    }

    public class AsynEvent extends AsyncTask {

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        SoapObject response = null;

        @Override
        protected Object doInBackground(Object[] params) {
            response = WebServiceSoapGet.EventoAberto(sharedPreferences.getInt("codUsuario", 0), bundle.getInt("bundle_perda"));
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
                        //só deixo para saber qual codigo do subgrupo estou usando
                        Integer.parseInt(obj.getProperty("CodigoTile").toString());
                        int quantidade = Integer.parseInt(obj.getProperty("Pendencia").toString());

                        if (cod == 145) {
                            if (quantidade >= 0) {
                                btDemarcaConhecida.setTextColor(Color.RED);
                                btDemarcaConhecida.setText(quantidade + "");
                            }
                        }
                    }
                }
            }
        }

    }
}
