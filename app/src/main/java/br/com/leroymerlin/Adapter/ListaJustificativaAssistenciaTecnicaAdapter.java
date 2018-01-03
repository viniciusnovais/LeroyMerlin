package br.com.leroymerlin.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.model.AssistenciaTecnica;

/**
 * Created by PDA on 04/10/2017.
 */

public class ListaJustificativaAssistenciaTecnicaAdapter extends RecyclerView.Adapter<ListaJustificativaAssistenciaTecnicaAdapter.MyViewHolder> {

    private List<AssistenciaTecnica> lista;
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemOnClick itemOnClick;


    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnClickListener(ItemOnClick itemOnClick) {
        this.itemOnClick = itemOnClick;
    }

    public ListaJustificativaAssistenciaTecnicaAdapter(Context context, List<AssistenciaTecnica> lista) {
        this.lista = lista;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ListaJustificativaAssistenciaTecnicaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_assistencia_tecnica_item, parent, false);

        MyViewHolder mv = new MyViewHolder(v);

        return mv;
    }

    @Override
    public void onBindViewHolder(ListaJustificativaAssistenciaTecnicaAdapter.MyViewHolder holder, int position) {

        AssistenciaTecnica a = lista.get(position);

        holder.tvfilial.setText(a.getFilial());

        holder.tvValorNf.setText(String.format("%.2f",Float.parseFloat(a.getValorTotal())).replaceAll("[.]",","));

        holder.tvSerie.setText(a.getSerie());

        holder.tvNota.setText(a.getNumNota());

        holder.tvCliente.setText(a.getNomeCliente());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        try {
            ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(a.getDataNota().substring(8, 10)));
            ca.set(Calendar.MONTH, Integer.parseInt(a.getDataNota().substring(5, 7)) - 1);
            ca.set(Calendar.YEAR, Integer.parseInt(a.getDataNota().substring(0, 4)));
            ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(a.getDataNota().substring(11, 13)));
            ca.set(Calendar.MINUTE, Integer.parseInt(a.getDataNota().substring(14, 16)));
            ca.set(Calendar.SECOND, Integer.parseInt(a.getDataNota().substring(17, 19)));
        } catch (Exception e) {
            e.printStackTrace();
            holder.tvDataNf.setText("Não informado");

        }

        holder.tvDataNf.setText(sdf.format(ca.getTime()));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void remove(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, lista.size());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvfilial, tvNota, tvSerie, tvCliente, tvDataNf, tvValorNf;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvfilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvNota = (TextView) itemView.findViewById(R.id.tvNota);
            tvSerie = (TextView) itemView.findViewById(R.id.tvSerie);
            tvCliente = (TextView) itemView.findViewById(R.id.tvCliente);
            tvValorNf = (TextView) itemView.findViewById(R.id.tvValorNf);
            tvDataNf = (TextView) itemView.findViewById(R.id.tvDataNf);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }
}
