package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Adapter.Atendimento2Adapter;
import br.com.leroymerlin.Adapter.Comercio2Adapter;
import br.com.leroymerlin.R;

/**
 * Created by PDA on 30/01/2017.
 */

public class Comercio2Activity extends AppCompatActivity {

    private List<String> lista= new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Comercio2Adapter adapter;
    private AlertDialog dialog=null;
    private  TextView tvNomeActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_logo);
        tvNomeActionBar= (TextView) findViewById(R.id.tvNomeActionBar);
        tvNomeActionBar.setText(getIntent().getExtras().getString("tituloBar"));
        setContentView(R.layout.activity_comercio2);

        for (int i=0;i<5;i++){
            lista.add("texto, teste");
        }

        mRecyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(llm);

        adapter = new Comercio2Adapter(this,lista);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("intent activity", data+"");
        Bitmap photo, originalBitmap,resizedBitmap;
        Uri uriImagem;
        final View v = getLayoutInflater().inflate(R.layout.frame_image, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(Comercio2Activity.this);
        builder.setView(v);
        ImageView image = (ImageView) v.findViewById(R.id.image);
        Button btEnviarFoto = (Button) v.findViewById(R.id.btSalvaFoto);
        Button btCancelarFoto = (Button)v.findViewById(R.id.btCancelarFoto);
        if (resultCode==RESULT_OK){
            if (requestCode==0){
                    photo = (Bitmap)data.getExtras().get("data");
                    originalBitmap = photo;
                    resizedBitmap = Bitmap.createScaledBitmap(
                            originalBitmap, 530, 530, false);

                    image.setImageBitmap(resizedBitmap);
                    dialog = builder.create();
                    dialog.show();
                    dialog.getWindow().setLayout(700, 800);

                }else if (requestCode==1){
                    String[] colunaCaminhoArquivo = { MediaStore.Images.Media.DATA };
                    uriImagem = data.getData();
                    Cursor cursor = getContentResolver().query(
                            uriImagem, colunaCaminhoArquivo, null, null, null);
                    cursor.moveToFirst();
                    int colunaIndex = cursor
                            .getColumnIndex(colunaCaminhoArquivo[0]);
                    String caminhoImagem = cursor.getString(colunaIndex);
                    cursor.close();
                    uriImagem = Uri.fromFile(new File(caminhoImagem));
                    try {
                        photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uriImagem);
                        originalBitmap = photo;
                        resizedBitmap = Bitmap.createScaledBitmap(
                                originalBitmap, 530, 530, false);
                        image.setImageBitmap(resizedBitmap);

                        dialog = builder.create();
                        dialog.show();
                        dialog.getWindow().setLayout(700, 800);
                    } catch (Exception e) {
                    }
                }
        }
        
        btEnviarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Salvo", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btCancelarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
    }
}
