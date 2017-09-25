package br.com.leroymerlin.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Grafico;
import br.com.leroymerlin.R;
import br.com.leroymerlin.dao.GraficoDao;
import br.com.leroymerlin.util.CreateChart;

/**
 * Created by PDA on 31/08/2017.
 */

public class FragmentGrafico extends Fragment {

    private int tipoGrafico;
    private int[] colors;
    private int cod;
    private List<BarEntry> lista = new ArrayList<>();
    private List<String> legendas = new ArrayList<>();

    public static FragmentGrafico novaInstancia(int tamanho, int[] colors, int cod) {
        FragmentGrafico f = new FragmentGrafico();
        Bundle args = new Bundle();
        args.putInt("tipoGrafico", tamanho);
        args.putIntArray("colors", colors);
        args.putInt("cod", cod);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tipoGrafico = getArguments().getInt("tipoGrafico");
        colors = getArguments().getIntArray("colors");
        cod = getArguments().getInt("cod");

        GraficoDao graficoDao = new GraficoDao(getContext().getApplicationContext());
        lista = lista(graficoDao.listar(), tipoGrafico);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grafico, container, false);

        BarChart chart = (BarChart) v.findViewById(R.id.chart);
        CreateChart.barChart(getContext().getApplicationContext(), chart, lista, colors, legendas);

        return v;
    }

    public List<BarEntry> lista(List<Grafico> lista, int tipoGrafico) {
        List<BarEntry> list = new ArrayList<>();
        List<Grafico> listaAux = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getTipoGrafico() == tipoGrafico) {
                listaAux.add(lista.get(i));
            }
        }

        for (int i = 0; i < listaAux.size(); i++) {
            legendas.add(listaAux.get(i).getDescricao());
            list.add(new BarEntry(i, listaAux.get(i).getValor()));
        }
        return list;
    }
}
