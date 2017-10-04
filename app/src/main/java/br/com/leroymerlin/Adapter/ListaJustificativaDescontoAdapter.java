package br.com.leroymerlin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.leroymerlin.R;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 30/01/2017.
 */

public class ListaJustificativaDescontoAdapter extends RecyclerView.Adapter<ListaJustificativaDescontoAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ItemOnClick itemOnClick;
    //listaTeste
    private List<Desconto> lista;

    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnItemClick(ItemOnClick itemClick) {
        this.itemOnClick = itemClick;
    }


    public ListaJustificativaDescontoAdapter(Context c, List<Desconto> lista) {
        this.context = c;
        this.lista = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.list_atendimento_item, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        Desconto d = lista.get(position);

        holder.tvFilial.setText(d.getFilial());

        holder.tvNota.setText(d.getNumNota());

        holder.tvSecao.setText(d.getSecao());

        holder.tvSerie.setText(d.getSerie());

        holder.tvLm.setText(d.getCodLm());

        holder.tvDescricao.setText(d.getDescricao());

        holder.tvOcorrencia.setText(d.getOcorrencia());

        holder.tvVendedor.setText(d.getVendedor());

        holder.tvValorVenda.setText(String.format("%.2f", d.getValorVenda()));

        holder.tvOperador.setText(d.getOperador());

        holder.tvDataHora.setText(d.getData());

        holder.tvNf.setText(String.format("%.2f", d.getValorNF()).replaceAll("[.]", ","));

        holder.tvPerc.setText(String.format("%.2f", d.getPercentual()).replaceAll("[.]", ","));

        holder.tvNome.setText(d.getNomeCliente());

        holder.tvCpf.setText(d.getCpfCliente());

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
        private TextView tvFilial, tvNota, tvSecao, tvSerie, tvLm, tvDescricao, tvOcorrencia, tvDataHora,
                tvNf, tvPerc, tvNome, tvCpf, tvVendedor, tvValorVenda, tvOperador;


        public MyViewHolder(final View itemView) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.linearLayoutJustificativa);
            tvFilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvNota = (TextView) itemView.findViewById(R.id.tvNota);
            tvSecao = (TextView) itemView.findViewById(R.id.tvSecao);
            tvSerie = (TextView) itemView.findViewById(R.id.tvSerie);
            tvLm = (TextView) itemView.findViewById(R.id.tvLm);
            tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            tvOcorrencia = (TextView) itemView.findViewById(R.id.tvOcorrencia);
            tvDataHora = (TextView) itemView.findViewById(R.id.tvDataHora);
            tvNf = (TextView) itemView.findViewById(R.id.tvNf);
            tvPerc = (TextView) itemView.findViewById(R.id.tvPerc);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvCpf = (TextView) itemView.findViewById(R.id.tvCpf);
            tvVendedor = (TextView) itemView.findViewById(R.id.tvVendedor);
            tvValorVenda = (TextView) itemView.findViewById(R.id.tvVenda);
            tvOperador = (TextView) itemView.findViewById(R.id.tvOperador);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }
}
