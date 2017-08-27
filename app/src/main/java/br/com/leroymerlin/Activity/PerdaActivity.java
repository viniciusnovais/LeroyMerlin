package br.com.leroymerlin.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Adapter.InventarioAdapter;
import br.com.leroymerlin.Adapter.PerdaAdapter;
import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGet;

/**
 * Created by PDA on 06/02/2017.
 */

public class PerdaActivity extends AppCompatActivity {
    private ListView listView;
    private PerdaAdapter adapter;
    private SoapObject response = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perda);

        listView= (ListView) findViewById(R.id.listView);

        AsyncListaInventario task= new AsyncListaInventario();
        task.execute();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                View v = adapterView.getChildAt(position);
                TextView tvNome= (TextView) v.findViewById(R.id.tvText);
                Intent i = new Intent(PerdaActivity.this, Perda2Activity.class);
                i.putExtra("tituloBar",tvNome.getText().toString());
                startActivity(i);
            }
        });
    }

    public class AsyncListaInventario extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            response = WebServiceSoapGet.EventoAbertoNivel2(3+"");
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            List<SoapObject> lista= new ArrayList<>();
            for (int i =0;i<response.getPropertyCount();i++) {
                lista.add((SoapObject) response.getProperty(i));
            }

            adapter= new PerdaAdapter(lista,PerdaActivity.this);
            listView.setAdapter(adapter);
        }
    }
}
