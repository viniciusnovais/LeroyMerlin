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
import br.com.leroymerlin.model.MargemNegativa;

/**
 * Created by PDA on 06/10/2017.
 */

public class ListaJustificativaMargemNegativaAdapter extends RecyclerView.Adapter<ListaJustificativaMargemNegativaAdapter.MyViewHolder> {
    private List<MargemNegativa> lista;
    private Context context;
    private LayoutInflater layoutInflater;
    private ItemOnClick itemOnClick;

    public interface ItemOnClick {
        void onClick(int position);
    }

    public void setOnItemClick(ItemOnClick itemClick) {
        this.itemOnClick = itemClick;
    }

    public ListaJustificativaMargemNegativaAdapter(Context context, List<MargemNegativa> lista) {
        this.lista = lista;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ListaJustificativaMargemNegativaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_margem_negativa_item, parent, false);
        MyViewHolder mv = new MyViewHolder(v);

        return mv;
    }

    @Override
    public void onBindViewHolder(ListaJustificativaMargemNegativaAdapter.MyViewHolder holder, int position) {

        MargemNegativa m = lista.get(position);


        holder.tvFilial.setText(m.getFilial());

        holder.tvDescricao.setText(m.getDescricao());

        holder.tvCusto.setText(m.getCusto());

        holder.tvEncargos.setText(m.getEncargos());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        Calendar ca = Calendar.getInstance();
        try {
            ca.set(Calendar.DAY_OF_MONTH, Integer.parseInt(m.getDtAtuest().substring(8, 10)));
            ca.set(Calendar.MONTH, Integer.parseInt(m.getDtAtuest().substring(5, 7)) - 1);
            ca.set(Calendar.YEAR, Integer.parseInt(m.getDtAtuest().substring(0, 4)));
            ca.set(Calendar.HOUR_OF_DAY, Integer.parseInt(m.getDtAtuest().substring(11, 13)));
            ca.set(Calendar.MINUTE, Integer.parseInt(m.getDtAtuest().substring(14, 16)));
            ca.set(Calendar.SECOND, Integer.parseInt(m.getDtAtuest().substring(17, 19)));
        } catch (Exception e) {
            e.printStackTrace();
            holder.tvData.setText("Não informado");

        }
        holder.tvData.setText(sdf.format(ca.getTime()));

        holder.tvMargem.setText(m.getMargem());

        holder.tvQtde.setText(m.getQuantidade());

        holder.tvPreco.setText(String.format("%.2f", Float.parseFloat(m.getPrecoVenda())).replaceAll("[.]", ","));

        holder.tvLm.setText(m.getCodigoProduto());

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
        public TextView tvFilial, tvLm, tvMargem, tvDescricao, tvPreco, tvQtde, tvData, tvCusto, tvEncargos;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvFilial = (TextView) itemView.findViewById(R.id.tvFilial);
            tvLm = (TextView) itemView.findViewById(R.id.tvLm);
            tvMargem = (TextView) itemView.findViewById(R.id.tvMargem);
            tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            tvPreco = (TextView) itemView.findViewById(R.id.tvPreco);
            tvQtde = (TextView) itemView.findViewById(R.id.tvQtde);
            tvData = (TextView) itemView.findViewById(R.id.tvData);
            tvCusto = (TextView) itemView.findViewById(R.id.tvCusto);
            tvEncargos = (TextView) itemView.findViewById(R.id.tvEncargos);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemOnClick.onClick(getAdapterPosition());
        }
    }
}
