package br.com.leroymerlin.Adapter;

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
import br.com.leroymerlin.model.DemarcaConhecida;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 03/10/2017.
 */

public class ListaJustificativaDemarcaAdapter extends RecyclerView.Adapter<ListaJustificativaDemarcaAdapter.MyViewHolder> {

    private List<DemarcaConhecida> lista;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ListaJustificativaCancelamentoAdapter.ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnItemClick(ListaJustificativaCancelamentoAdapter.ItemOnClick itemClick) {
        this.itemOnClick = itemClick;
    }

    public ListaJustificativaDemarcaAdapter(Context context, List<DemarcaConhecida> lista) {
        this.lista = lista;
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ListaJustificativaDemarcaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.list_demarca_conhecida_item, parent, false);
        MyViewHolder mv = new MyViewHolder(v);

        return mv;
    }

    @Override
    public void onBindViewHolder(ListaJustificativaDemarcaAdapter.MyViewHolder holder, int position) {
        //setar os valores na lista

        DemarcaConhecida d = lista.get(position);

        holder.tvFilial.setText(d.getFilial());

        holder.tvNota.setText(d.getNumNota());

        holder.tvSerie.setText(d.getSerie());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        try {
            ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d.getDtAtuest().substring(8, 10)));
            ca.set(Calendar.MONTH, Integer.parseInt(d.getDtAtuest().substring(5, 7)) - 1);
            ca.set(Calendar.YEAR, Integer.parseInt(d.getDtAtuest().substring(0, 4)));
            ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(d.getDtAtuest().substring(11, 13)));
            ca.set(Calendar.MINUTE, Integer.parseInt(d.getDtAtuest().substring(14, 16)));
            ca.set(Calendar.SECOND, Integer.parseInt(d.getDtAtuest().substring(17, 19)));
        } catch (Exception e) {
            e.printStackTrace();
            holder.tvData.setText("Não informado");

        }

        holder.tvData.setText(sdf.format(ca.getTime()));

        holder.tvValorNf.setText(String.format("%.2f",Float.parseFloat(d.getValorTotal())).replaceAll("[.]",","));

        holder.tvLm.setText(d.getCodigoProduto());

        holder.tvQtde.setText(d.getQuantidade());

        holder.tvDescricao.setText(d.getDescricao());

        holder.tvNomeUsuario.setText(d.getNomeUser());
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
        public TextView tvFilial, tvNota, tvSerie, tvDescricao, tvLm, tvQtde, tvNomeUsuario, tvValorNf, tvData;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvFilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            tvNota = (TextView) itemView.findViewById(R.id.tvNota);
            tvSerie = (TextView) itemView.findViewById(R.id.tvSerie);
            tvLm = (TextView) itemView.findViewById(R.id.tvLm);
            tvQtde = (TextView) itemView.findViewById(R.id.tvQtde);
            tvNomeUsuario = (TextView) itemView.findViewById(R.id.tvNomeUsuario);
            tvValorNf = (TextView) itemView.findViewById(R.id.tvValorNf);
            tvData = (TextView) itemView.findViewById(R.id.tvData);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }
}
