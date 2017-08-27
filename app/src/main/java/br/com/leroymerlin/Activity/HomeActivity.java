package br.com.leroymerlin.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapGet;

public class HomeActivity extends AbsRuntimePermission {
    private ImageButton btLogistica,btGestao,btInventario,btPerda,btComercio,btAtendimento;
    private TextView tvLogistica,tvGestao,tvInventario,tvPerda,tvComercio,tvAtendimento;
    static boolean errored;
    SoapObject response=null;
    public static final int REQUEST_PERMISSION = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        requestAppPermissions(new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,}, R.string.msg, REQUEST_PERMISSION);


        //Pegando Ids dos buttons
        btLogistica=(ImageButton) findViewById(R.id.btLogistica);
        btGestao=(ImageButton)findViewById(R.id.btGestao);
        btInventario=(ImageButton)findViewById(R.id.btInventario);
        btPerda=(ImageButton)findViewById(R.id.btPerda);
        btComercio=(ImageButton)findViewById(R.id.btComercio);
        btAtendimento=(ImageButton) findViewById(R.id.btAtendimento);

        //Pegando ids dos TextViews status
        tvLogistica=(TextView) findViewById(R.id.tvLogistica);
        tvGestao=(TextView) findViewById(R.id.tvGestao);
        tvInventario=(TextView) findViewById(R.id.tvInventario);
        tvPerda=(TextView) findViewById(R.id.tvPerda);
        tvComercio=(TextView) findViewById(R.id.tvComercio);
        tvAtendimento=(TextView) findViewById(R.id.tvAtendimento);

        AsyncEventoWS task = new AsyncEventoWS();

        btAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,AtendimentoActivity.class);
                startActivity(i);
            }
        });

        btInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, InventarioActivity.class);
                startActivity(i);
            }
        });

        btPerda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,PerdaActivity.class);
                startActivity(i);
            }
        });
        btGestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,GestaoActivity.class);
                startActivity(i);
            }
        });

        btComercio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,ComercioActivity.class);
                startActivity(i);
            }
        });

        btLogistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,LogisticaActivity.class);
                startActivity(i);
            }
        });

        temporizador_30_30(task);

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
    }

    private class AsyncEventoWS extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            response = WebServiceSoapGet.EventoAberto();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(!errored){
                //Pegando resposta Pai
                List<SoapObject> subResponse = new ArrayList<>();
                for (int i =0;i<response.getPropertyCount();i++) {
                    subResponse.add((SoapObject)response.getProperty(i));
                }
                if (subResponse!=null) {
                    //SubResposta, Filho
                    for (SoapObject obj : subResponse){
                        int cod = Integer.parseInt(obj.getProperty("Codigo").toString());
                        int quantidade =Integer.parseInt(obj.getProperty("Quantidade").toString());

                        if (cod == 132) {

                            if (quantidade > 0) {
                                tvAtendimento.setVisibility(View.VISIBLE);
                                tvAtendimento.setText(quantidade+"");
                            }
                        }
                        if (cod == 133) {
                            if (quantidade > 0) {
                                tvLogistica.setVisibility(View.VISIBLE);
                                tvLogistica.setText(quantidade+"");
                            }
                        }
                        if (cod == 134) {
                            if (quantidade > 0) {
                                tvComercio.setVisibility(View.VISIBLE);
                                tvComercio.setText(quantidade+"");
                            }
                        }
                        if (cod == 135) {
                            if (quantidade > 0) {
                                tvGestao.setVisibility(View.VISIBLE);
                                tvGestao.setText(quantidade+"");
                            }
                        }
                        if (cod == 136) {
                            if (quantidade > 0) {
                                tvInventario.setVisibility(View.VISIBLE);
                                tvInventario.setText(quantidade+"");
                            }
                        }

                        if (cod == 137) {
                            if (quantidade > 0) {
                                tvPerda.setVisibility(View.VISIBLE);
                                tvPerda.setText(quantidade+"");
                            }
                        }
                    }
                } else{
                    //Set Error message
                    Toast.makeText(getApplicationContext(),"Falha",Toast.LENGTH_SHORT).show();
                }
                //Error status is true
            }else{
                Toast.makeText(getApplicationContext(),"Erro invocando o WebService",Toast.LENGTH_SHORT).show();
            }
            //Re-initialize Error Status to False
            errored = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Mensagem de Bem-Vindo ao usuário
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.PREF_NAME,MODE_PRIVATE);
        TextView tv= ((TextView)findViewById(R.id.tvWelcome));
        tv.setText("Bem-Vindo, "+sharedpreferences.getString("nome",""));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btSair:

                //Deslogar
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.PREF_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear().commit();

                Intent i = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }

    }

    public void temporizador_30_30(final AsyncEventoWS task) {
        final long TEMPO = (1000 * 10); // atualiza o site a cada 30 segundos
        Log.w("inicio","inicio");
        Timer timer = null;
        task.execute();
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                public void run() {
                    try {
                        //chamar metodo
                        task.execute();
                        Log.w("chama","10");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }
}
