package br.com.leroymerlin.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import org.ksoap2.serialization.SoapObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGetInfoGrafico;
import br.com.leroymerlin.util.CreateChart;

/**
 * Created by PDA on 31/08/2017.
 */

public class FragmentGrafico extends Fragment {

    private int tamanho;
    private int[] colors;
    private int cod;


    public static FragmentGrafico novaInstancia(int tamanho, int[] colors, int cod) {
        FragmentGrafico f = new FragmentGrafico();
        Bundle args = new Bundle();
        args.putInt("tamanho", tamanho);
        args.putIntArray("colors", colors);
        args.putInt("cod", cod);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tamanho = getArguments().getInt("tamanho");
        colors = getArguments().getIntArray("colors");
        cod = getArguments().getInt("cod");

//        AsynGrafico task = new AsynGrafico();
//        task.execute(cod);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grafico, container, false);

        BarChart chart = (BarChart) v.findViewById(R.id.chart);
        CreateChart.barChart(getContext().getApplicationContext(), chart, listaTeste(tamanho), colors);

        return v;
    }

    public List<BarEntry> listaTeste(int tamanho) {
        List<BarEntry> list = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            list.add(new BarEntry(i, (float) i));
        }

        return list;
    }


    public class AsynGrafico extends AsyncTask<Integer, Void, Void> {

        public SoapObject soapObject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Integer... params) {
            soapObject = WebServiceSoapGetInfoGrafico.infoGrafico(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
