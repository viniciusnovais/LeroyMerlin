package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.leroymerlin.R;

/**
 * Created by PDA on 31/08/2017.
 */

public class PerdaGrupoActivity extends AppCompatActivity {

    private Button btDemarcaConhecida;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_prevencao);

        btDemarcaConhecida = (Button) findViewById(R.id.btDemarcaConhecida);

        bundle = getIntent().getBundleExtra("bundle_quantidade");

        btDemarcaConhecida.setText(bundle.getInt("demarca_conhecida") + "");

        btDemarcaConhecida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerdaGrupoActivity.this, GraficoActivity.class);
                startActivity(i);
            }
        });


    }
}
