package br.com.leroymerlin.util;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * Created by PDA on 05/07/2017.
 */

public class MyValueFormatter implements IAxisValueFormatter {
    private String[] mFormat;

    public MyValueFormatter(String[] mFormat) {

        this.mFormat=mFormat;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat[(int) value];
    }

}
