package br.com.leroymerlin.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by PDA on 05/07/2017.
 */

public class MyValueFormatter implements IAxisValueFormatter, IValueFormatter {
    private List<String> mFormat;
    private List<Entry> lista;
    private float valor;

    public MyValueFormatter(List<Entry> lista, float valor) {
        this.valor = valor;
        this.lista = lista;
    }

    public MyValueFormatter(List<String> mFormat) {

        this.mFormat = mFormat;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.get((int) value);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return null;
    }
}
