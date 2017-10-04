package br.com.leroymerlin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 30/01/2017.
 */

public class ListaJustificativaCancelamentoAdapter extends RecyclerView.Adapter<ListaJustificativaCancelamentoAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Cancelamento> lista;
    private ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnItemClick(ItemOnClick itemClick) {
        this.itemOnClick = itemClick;
    }


    public ListaJustificativaCancelamentoAdapter(Context c, List<Cancelamento> lista) {
        this.context = c;
        this.lista = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.list_cancelamento_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Cancelamento c = lista.get(position);


        holder.tvFilial.setText(c.getFilial());

        holder.tvNota.setText((String.format("%d", (long) c.getNumNota())));

        holder.tvSerie.setText(c.getSerie());

        holder.tvValorNota.setText((String.format("%.2f", c.getValorTotal()).replaceAll("[.]", ",")));

        holder.tvCaixa.setText((String.format("%d", (long) c.getNumCaixa())));

        holder.tvUsuarioCancel.setText(c.getUsuarioCancel());

        holder.tvCondPagto.setText(c.getCodigoPagto());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        try {
            ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(c.getDataCancelamento().substring(8, 10)));
            ca.set(Calendar.MONTH, Integer.parseInt(c.getDataCancelamento().substring(5, 7)) - 1);
            ca.set(Calendar.YEAR, Integer.parseInt(c.getDataCancelamento().substring(0, 4)));
            ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(c.getDataCancelamento().substring(11, 13)));
            ca.set(Calendar.MINUTE, Integer.parseInt(c.getDataCancelamento().substring(14, 16)));
            ca.set(Calendar.SECOND, Integer.parseInt(c.getDataCancelamento().substring(17, 19)));
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

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void remove(int position) {
        lista.remove(position);
        notifyItemRemoved(position);
        notifyItemChanged(position, lista.size());

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout ll;
        private TextView tvFilial, tvNota, tvSerie, tvDataNf, tvUsuarioCancel, tvCondPagto, tvCaixa, tvValorNota;


        public MyViewHolder(final View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.linearLayoutJustificativa);
            tvFilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvNota = (TextView) itemView.findViewById(R.id.tvNota);
            tvSerie = (TextView) itemView.findViewById(R.id.tvSerie);
            tvDataNf = (TextView) itemView.findViewById(R.id.tvDataNF);
            tvCaixa = (TextView) itemView.findViewById(R.id.tvCaixa);
            tvUsuarioCancel = (TextView) itemView.findViewById(R.id.tvUsuarioCan);
            tvCondPagto = (TextView) itemView.findViewById(R.id.tvCondPagto);
            tvValorNota = (TextView) itemView.findViewById(R.id.tvValorNf);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }

}

