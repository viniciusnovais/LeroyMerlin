package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import br.com.leroymerlin.R;

/**
 * Created by PDA on 31/08/2017.
 */

public class ComercioGrupoActivity extends AppCompatActivity {

    private Button btMargemNegativa, btAssistenciaTec;
    private Bundle bundle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_comercio);

        btMargemNegativa = (Button) findViewById(R.id.btMargemNegativa);
        btAssistenciaTec = (Button) findViewById(R.id.btAssistenciaTec);

        bundle = getIntent().getBundleExtra("bundle_quantidade");

        btMargemNegativa.setText(bundle.getInt("margem_negativa") + "");
        btAssistenciaTec.setText(bundle.getInt("assistencia_tec") + "");

        btMargemNegativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComercioGrupoActivity.this, GraficoActivity.class);
                startActivity(i);
            }
        });

        btAssistenciaTec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComercioGrupoActivity.this, GraficoActivity.class);
                startActivity(i);

            }
        });

    }
}
