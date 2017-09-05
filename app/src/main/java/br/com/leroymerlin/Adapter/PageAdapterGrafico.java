package br.com.leroymerlin.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import br.com.leroymerlin.Fragment.FragmentGrafico;
import br.com.leroymerlin.R;

/**
 * Created by PDA on 31/08/2017.
 */

public class PageAdapterGrafico extends FragmentPagerAdapter {

    private String[] titulosFragmentsTabs;
    private int[] colorsHojePorFilial = new int[]{R.color.colorBlue};
    private int[] colorsNoMes = new int[]{R.color.colorRed};
    private int[] colorsUltimosMeses = new int[]{R.color.colorGreenLight};
    private int[] colorsPorRegional = new int[]{R.color.colorGreen};
    private int cod;

    public PageAdapterGrafico(FragmentManager fm, String[] titulosFragmentsTabs, int cod) {
        super(fm);
        this.titulosFragmentsTabs = titulosFragmentsTabs;
        this.cod = cod;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return new FragmentGrafico().novaInstancia(45, colorsHojePorFilial, cod);
            case 1:
                return new FragmentGrafico().novaInstancia(5, colorsPorRegional, cod);
            case 2:
                return new FragmentGrafico().novaInstancia(30, colorsNoMes, cod);
            case 3:
                return new FragmentGrafico().novaInstancia(2, colorsUltimosMeses, cod);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.titulosFragmentsTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.titulosFragmentsTabs[position];
    }
}
