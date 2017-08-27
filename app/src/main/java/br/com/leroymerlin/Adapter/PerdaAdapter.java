package br.com.leroymerlin.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import br.com.leroymerlin.R;

/**
 * Created by PDA on 06/02/2017.
 */

public class PerdaAdapter extends BaseAdapter {

    private Context context;
    private List<SoapObject> lista;

    public PerdaAdapter(List<SoapObject> lista, Context context){
        this.context=context;
        this.lista=lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.list_perda_item,null);

        TextView tvQuantidade= (TextView) v.findViewById(R.id.tvQuantidade);
        tvQuantidade.setText(lista.get(position).getProperty("Codigo").toString());

        TextView tvText = (TextView) v.findViewById(R.id.tvText);
        tvText.setText(lista.get(position).getProperty("Nome").toString());


        return v;
    }
}
