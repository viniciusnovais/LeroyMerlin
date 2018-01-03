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
import br.com.leroymerlin.model.FaturamentoPendente;

/**
 * Created by PDA on 30/01/2017.
 */

public class ListaJustificativaFaturamentoAdapter extends RecyclerView.Adapter<ListaJustificativaFaturamentoAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private ItemOnClick itemOnClick;
    //listaTeste
    private List<FaturamentoPendente> lista;

    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnItemClick(ItemOnClick itemClick) {
        this.itemOnClick = itemClick;
    }


    public ListaJustificativaFaturamentoAdapter(Context c, List<FaturamentoPendente> lista) {
        this.context = c;
        this.lista = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.list_faturamento_pendente_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        FaturamentoPendente f = lista.get(position);


        holder.tvFilial.setText(f.getFilial());

        holder.tvPedido.setText(f.getPedido());

        holder.tvCaixa.setText(f.getNumCaixa() + "");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        try {
            ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(f.getDataPedido().substring(8, 10)));
            ca.set(Calendar.MONTH, Integer.parseInt(f.getDataPedido().substring(5, 7)) - 1);
            ca.set(Calendar.YEAR, Integer.parseInt(f.getDataPedido().substring(0, 4)));
            ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(f.getDataPedido().substring(11, 13)));
            ca.set(Calendar.MINUTE, Integer.parseInt(f.getDataPedido().substring(14, 16)));
            ca.set(Calendar.SECOND, Integer.parseInt(f.getDataPedido().substring(17, 19)));
        } catch (Exception e) {
            e.printStackTrace();
            holder.tvDataPedido.setText("Não informado");

        }

        holder.tvDataPedido.setText(sdf.format(ca.getTime()));

        holder.tvValorParcial.setText(String.format("%.2f", Float.parseFloat(f.getValorParcial())).replaceAll("[.]", ","));

        Calendar ca2 = Calendar.getInstance();
        try {
            ca2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(f.getDataEntrega().substring(8, 10)));
            ca2.set(Calendar.MONTH, Integer.parseInt(f.getDataEntrega().substring(5, 7)) - 1);
            ca2.set(Calendar.YEAR, Integer.parseInt(f.getDataEntrega().substring(0, 4)));
            ca2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(f.getDataEntrega().substring(11, 13)));
            ca2.set(Calendar.MINUTE, Integer.parseInt(f.getDataEntrega().substring(14, 16)));
            ca2.set(Calendar.SECOND, Integer.parseInt(f.getDataEntrega().substring(17, 19)));
        } catch (Exception e) {
            e.printStackTrace();
            holder.tvDataPedido.setText("Não informado");

        }

        holder.tvDataEntrega.setText(sdf.format(ca2.getTime()));

        holder.tvValorNf.setText(String.format("%.2f", Float.parseFloat(f.getValorTotal())).replaceAll("[.]", ","));

        holder.tvNomeCliente.setText(f.getNomeCliente());

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
        private TextView tvFilial, tvPedido, tvCaixa, tvDataPedido, tvValorParcial, tvValorNf, tvDataEntrega, tvNomeCliente;


        public MyViewHolder(final View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.linearLayoutJustificativa);
            tvFilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvPedido = (TextView) itemView.findViewById(R.id.tvPedido);
            tvCaixa = (TextView) itemView.findViewById(R.id.tvCaixa);
            tvDataPedido = (TextView) itemView.findViewById(R.id.tvDataPedido);
            tvValorParcial = (TextView) itemView.findViewById(R.id.tvValorParcial);
            tvValorNf = (TextView) itemView.findViewById(R.id.tvValorNf);
            tvDataEntrega = (TextView) itemView.findViewById(R.id.tvDataEntrega);
            tvNomeCliente = (TextView) itemView.findViewById(R.id.tvNomeCliente);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }

}

