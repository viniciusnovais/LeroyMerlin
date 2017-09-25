package br.com.leroymerlin.util;

import android.content.Context;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

/**
 * Created by PDA on 31/08/2017.
 */

public class CreateChart {

    //usar esse metodo quando eu estiver recebendo a lista correta, para setar as legendas na horizontal
    //passarei por um vetor de String
    //public static void barChart(Context context, List<BarEntry> barEntries, String[] quarters, int[] colors) {

    public static BarChart barChart(Context context, BarChart barChart, List<BarEntry> barEntries, int[] colors, List<String> legendas) {
        BarDataSet barDataSet = new BarDataSet(barEntries, "Dates");
        barDataSet.setColors(colors, context);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.5f);
        //data.setValueFormatter(new MyValueFormatter(new MyValueFormatter(barDataSet.getValues())));
        data.setValueTextSize(15f);
        barChart.setData(data);
        //barChart.animateY(2000, Easing.EasingOption.EaseInQuad);
        barChart.fitScreen();
        barChart.setVisibleXRangeMaximum(5);
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
        xAxis.setLabelRotationAngle(60f);
        xAxis.setValueFormatter(new MyValueFormatter(legendas));


        YAxis rightyAxis = barChart.getAxisRight();
        rightyAxis.setEnabled(false);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setAxisMinimum(0f);

        Legend legend = barChart.getLegend();
        legend.setEnabled(false);

        return barChart;
    }
}
