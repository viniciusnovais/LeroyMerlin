package br.com.leroymerlin.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import br.com.leroymerlin.Adapter.PageAdapterGrafico;
import br.com.leroymerlin.R;

/**
 * Created by PDA on 31/08/2017.
 */

public class GraficoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    private String[] titulos = new String[]{"H/ por Filial(R$)", "H/ por Região(R$)", "No mês por dia(R$)", "últimos meses(R$)",};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_graficos);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        //o tamanho do viewPager vai depender de quantos gráficos terão na seção
        //no momento deixei 4 que estou usando como teste o desconto
        //viewPager.setOffscreenPageLimit(4);
        int cod = getIntent().getIntExtra("cod", 0);
        viewPager.setAdapter(new PageAdapterGrafico(getSupportFragmentManager(), titulos, cod));
        tabLayout.setupWithViewPager(viewPager);
    }
}
