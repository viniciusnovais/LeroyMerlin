package br.com.leroymerlin.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import java.util.List;

import br.com.leroymerlin.R;

/**
 * Created by PDA on 27/01/2017.
 */

public class AtendimentoAdapter extends BaseAdapter {
    List<SoapObject> listaAtendimento;
    Context context;

    public AtendimentoAdapter(List<SoapObject> listaAtendimento, Context context){
        this.listaAtendimento=listaAtendimento;
        this.context=context;
    }

    @Override
    public int getCount() {
        return listaAtendimento.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAtendimento.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v= View.inflate(context, R.layout.list_atendimento_item,null);

        TextView tvTextAtende= (TextView) v.findViewById(R.id.tvTextAtendimento);
        TextView tvQuantidade = (TextView) v.findViewById(R.id.tvQuantidade);

        tvQuantidade.setText(listaAtendimento.get(position).getProperty("Codigo").toString());
        tvTextAtende.setText(listaAtendimento.get(position).getProperty("Nome").toString());

        return v;
    }
}
