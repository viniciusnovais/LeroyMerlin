package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
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

public class ComercioGrupoActivity extends AppCompatActivity {

    private Button btAssistenciaTec;
    private Bundle bundle;
    static boolean errored;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_comercio);

        btAssistenciaTec = (Button) findViewById(R.id.btAssistenciaTec);

        bundle = getIntent().getBundleExtra("bundle_comercio");

        btAssistenciaTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComercioGrupoActivity.this, GraficoActivity.class);
                i.putExtra("tipo", 305);
                startActivity(i);

            }
        });

        AsyncEvent task = new AsyncEvent();
        task.execute();
    }

    public class AsyncEvent extends AsyncTask {

        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        SoapObject response = null;

        @Override
        protected Object doInBackground(Object[] params) {
            response = WebServiceSoapGet.EventoAberto(sharedPreferences.getInt("codUsuario", 0), bundle.getInt("bundle_comercio"));
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
                        int codTile = Integer.parseInt(obj.getProperty("CodigoTile").toString());

                        if (cod == 149) {
                            if (quantidade >= 0) {
                                btAssistenciaTec.setTextColor(Color.RED);
                                btAssistenciaTec.setText(quantidade + "");
                            }
                        }
                    }
                }
            }
        }
    }
}
