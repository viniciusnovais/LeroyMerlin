package br.com.leroymerlin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        holder.tvData.setText(d.getDtAtuest());

        holder.tvValorNf.setText(d.getValorTotal());

        holder.tvLm.setText(d.getCodigoProduto());

        holder.tvQtde.setText(d.getQuantidade());

        holder.tvDescricao.setText(d.getDescricao());

        holder.tvNomeUsuario.setText(d.getNomeUser());
    }

    @Override
    public int getItemCount() {
        return lista.size();
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
