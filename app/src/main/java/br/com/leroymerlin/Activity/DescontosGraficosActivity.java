package br.com.leroymerlin.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.util.MyValueFormatter;

/**
 * Created by PDA on 30/08/2017.
 */


public class DescontosGraficosActivity extends AppCompatActivity {

    private BarChart barChart, barChart1, barChart2, barChart3;
    private int[] colorsHojePorFilial = new int[]{R.color.colorBlue};
    private int[] colorsNoMes = new int[]{R.color.colorRed};
    private int[] colorsUltimosMeses = new int[]{R.color.colorGreenLight};
    private int[] colorsPorRegional = new int[]{R.color.colorGreen};

    private String[] quarters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos_desconto);

        barChart = (BarChart) findViewById(R.id.chartBar);
        barChart1 = (BarChart) findViewById(R.id.chartBar1);
        barChart2 = (BarChart) findViewById(R.id.chartBar2);
        barChart3 = (BarChart) findViewById(R.id.chartBar3);

        //Poer filial
        List<BarEntry> entries = new ArrayList<>();
        quarters = new String[45];
        for (int i = 0; i < 45; i++) {
            entries.add(new BarEntry(Float.parseFloat(i + ""), 30f));
            quarters[i] = "set" + i;
        }
        createBarChart(barChart, colorsHojePorFilial, entries, quarters);
        //Por Regional
        List<BarEntry> entries1 = new ArrayList<>();
        quarters = new String[5];
        for (int i = 0; i < 5; i++) {
            entries1.add(new BarEntry(Float.parseFloat(i + ""), 25f));
            quarters[i] = "set" + i;
        }
        createBarChart(barChart1, colorsPorRegional, entries1, quarters);
        //No mes por dia
        quarters = new String[30];
        List<BarEntry> entries2 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            entries2.add(new BarEntry(Float.parseFloat(i + ""), 45f));
            quarters[i] = "set" + i;
        }
        createBarChart(barChart2, colorsNoMes, entries2, quarters);
        //ultimos meses
        quarters = new String[2];
        List<BarEntry> entries3 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            entries3.add(new BarEntry(Float.parseFloat(i + ""), 70f));
            quarters[i] = "set" + i;
        }
        createBarChart(barChart3, colorsUltimosMeses, entries3, quarters);

    }


    public void createBarChart(BarChart barChart, int[] colors, List<BarEntry> barEntries, String[] quarters) {

        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");

        barDataSet.setColors(colors, this);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        barChart.setData(data);
        barChart.animateY(3000, Easing.EasingOption.EaseInQuad);
        barChart.setFitBars(true);
        barChart.setPinchZoom(false);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setDescription(null);
        barChart.setKeepPositionOnRotation(true);
        barChart.setDoubleTapToZoomEnabled(true);
        barChart.invalidate();

        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setEnabled(true);
        xAxis.setTextSize(10f);
        xAxis.setLabelRotationAngle(80f);
        xAxis.setValueFormatter(new MyValueFormatter(quarters));


        YAxis rightyAxis = barChart.getAxisRight();
        rightyAxis.setEnabled(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);
    }
}
