package br.com.leroymerlin.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import br.com.leroymerlin.R;

/**
 * Created by PDA on 30/01/2017.
 */

public class Gestao2Adapter extends RecyclerView.Adapter<Gestao2Adapter.MyViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private AlertDialog dialogOpcoes=null;
    //listaTeste
    private List<String> lista;


    public Gestao2Adapter(Context c, List<String> lista) {
        this.context = c;
        this.lista = lista;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.list_gestao2_item, parent, false);
        LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearLayoutBox);
        MyViewHolder mvh = new MyViewHolder(v, ll);

        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tvJustificativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.tvJustificativa.setHeight(1000);
                holder.tvJustificativa.setFocusableInTouchMode(true);
            }
        });

        //Salvar e eliminar item da lista
        holder.btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Salvo", Toast.LENGTH_SHORT).show();
                int newPosition = holder.getAdapterPosition();
                lista.remove(newPosition);
                notifyItemRemoved(newPosition);
                notifyItemRangeRemoved(newPosition, getItemCount());
                //ll.setVisibility(View.GONE);
            }
        });
        //Botão cancelar e volta para a lista
        holder.btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.ll.setVisibility(View.GONE);
                holder.tvJustificativa.setHeight(90);
                holder.tvJustificativa.setFocusableInTouchMode(false);
            }
        });
        //Botão para tirar foto ou pegar imagem da Galeria
        holder.btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Camera().show();

            }
        });


    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public LinearLayout ll;
        private Button btSalvar, btCancelar,btCamera;
        private EditText tvJustificativa;

        public MyViewHolder(final View itemView, LinearLayout llBox) {
            super(itemView);
            ll = (LinearLayout) itemView.findViewById(R.id.linearLayoutJustificativa);
            btSalvar = (Button) itemView.findViewById(R.id.btSalvar);
            btCancelar = (Button) itemView.findViewById(R.id.btCancelar);
            btCamera= (Button) itemView.findViewById(R.id.btCamera);
            tvJustificativa=(EditText) itemView.findViewById(R.id.editJustificativa);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            ll.setVisibility(View.VISIBLE);
        }
    }

    public AlertDialog Camera() {

        //Dialog com as opçoes
        AlertDialog.Builder builderCamera = new AlertDialog.Builder(context);
        builderCamera.setTitle("opções");
        String[] item = new String[2];
        item[0] = "Câmera";
        item[1] = "Galeria";

        builderCamera.setItems(item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                switch (which) {
                    //Abrir Camera
                    case 0:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity)context).startActivityForResult(intent,0);
                        break;
                    //Abrir Galeria
                    case 1:
                        intent = new Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        ((Activity)context).startActivityForResult(intent,1);
                        break;
                    default:
                        break;
                }
            }
        });
        dialogOpcoes = builderCamera.create();
        return  dialogOpcoes;
    }


}
