package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Adapter.Atendimento2Adapter;
import br.com.leroymerlin.Adapter.AtendimentoAdapter;
import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGet;

/**
 * Created by PDA on 27/01/2017.
 */

public class AtendimentoActivity extends AppCompatActivity {

    private AtendimentoAdapter adapter;
    private ListView listView;
    private SoapObject response;
    private int cnt=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendimento);

        listView= (ListView) findViewById(R.id.listView);

        AsyncAtendimentoWS task = new AsyncAtendimentoWS();
        task.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                View v = adapterView.getChildAt(position);
                TextView tvNome= (TextView) v.findViewById(R.id.tvTextAtendimento);
                Intent i = new Intent(AtendimentoActivity.this, Atendimento2Activity.class);
                i.putExtra("tituloBar",tvNome.getText().toString());
                startActivity(i);
            }
        });



    }


    public class AsyncAtendimentoWS extends AsyncTask{
        @Override
        protected Object doInBackground(Object[] objects) {
            response= WebServiceSoapGet.EventoAbertoNivel2(1+"");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
           List<SoapObject> lista= new ArrayList<>();
           for (int i =0;i<response.getPropertyCount();i++) {
               lista.add((SoapObject) response.getProperty(i));
            }

            adapter = new AtendimentoAdapter(lista,AtendimentoActivity.this);
            listView.setAdapter(adapter);

        }
    }
}
