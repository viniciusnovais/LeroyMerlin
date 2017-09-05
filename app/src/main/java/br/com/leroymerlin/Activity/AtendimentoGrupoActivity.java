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
 * Created by PDA on 30/08/2017.
 */

public class AtendimentoGrupoActivity extends AppCompatActivity {

    private Button btDesconto, btCancelamentoCupom, btPedidoPendente;
    private Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_app);
        setContentView(R.layout.activity_grupo_atendimento);

        btDesconto = (Button) findViewById(R.id.btDesconto);
        btCancelamentoCupom = (Button) findViewById(R.id.btCancelamentoCupom);
        btPedidoPendente = (Button) findViewById(R.id.btPedidoPendente);

        //pegando as quantidades do serviço q estavam na outra activity
        bundle = getIntent().getBundleExtra("bundle_quantidade");

        btDesconto.setText(bundle.getInt("descontos") + "");
        btCancelamentoCupom.setText(bundle.getInt("cancelamento") + "");
        btPedidoPendente.setText(bundle.getInt("faturamento") + "");

        btDesconto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ir para tela dos Gráficos
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                startActivity(i);
            }
        });

        btCancelamentoCupom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                startActivity(i);
            }
        });

        btPedidoPendente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AtendimentoGrupoActivity.this, GraficoActivity.class);
                startActivity(i);
            }
        });
    }


}
