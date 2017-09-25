package br.com.leroymerlin.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActivityChooserView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    private Button btLogistica, btGestao, btInventario, btPerda, btComercio, btAtendimento;
    static boolean errored;
    SoapObject response = null;
    public static final int REQUEST_PERMISSION = 10;
    private Bundle bundle = new Bundle();

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
        btLogistica = (Button) findViewById(R.id.btLogistica);
        btGestao = (Button) findViewById(R.id.btGestao);
        btInventario = (Button) findViewById(R.id.btInventario);
        btPerda = (Button) findViewById(R.id.btPerda);
        btComercio = (Button) findViewById(R.id.btComercio);
        btAtendimento = (Button) findViewById(R.id.btAtendimento);


        btAtendimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, AtendimentoGrupoActivity.class);
                i.putExtra("bundle_atendimento", bundle);
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
                Intent i = new Intent(HomeActivity.this, PerdaGrupoActivity.class);
                i.putExtra("bundle_perda", bundle);
                startActivity(i);
            }
        });
        btGestao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, GestaoGrupoActivity.class);
                i.putExtra("bundle_gestao", bundle);
                startActivity(i);
            }
        });

        btComercio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ComercioGrupoActivity.class);
                i.putExtra("bundle_comercio", bundle);
                startActivity(i);
            }
        });

        btLogistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, LogisticaActivity.class);
                startActivity(i);
            }
        });

        AsyncEventoWS task = new AsyncEventoWS();
        temporizador_30_30(task);

    }

    @Override
    public void onPermissionsGranted(int requestCode) {
    }

    private class AsyncEventoWS extends AsyncTask {

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);

        @Override
        protected Object doInBackground(Object[] objects) {
            response = WebServiceSoapGet.EventoAberto(sharedpreferences.getInt("codUsuario", 0), sharedpreferences.getInt("codFilial", 0));
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (!errored) {
                //Pegando resposta Pai
                List<SoapObject> subResponse = new ArrayList<>();
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    subResponse.add((SoapObject) response.getProperty(i));
                }
                if (subResponse != null) {
                    //SubResposta, Filho
                    for (SoapObject obj : subResponse) {
                        int cod = Integer.parseInt(obj.getProperty("CodigoMenu").toString());
                        int codTile = Integer.parseInt(obj.getProperty("CodigoTile").toString());
                        int quantidade = Integer.parseInt(obj.getProperty("Pendencia").toString());

                        if (cod == 132) {

                            if (quantidade >= 0) {
                                btAtendimento.setTextColor(Color.RED);
                                btAtendimento.setText(quantidade + "");
                                bundle.putInt("bundle_atendimento", codTile);

                            }
                        }
                        if (cod == 133) {
                            if (quantidade >= 0) {
                                btLogistica.setTextColor(Color.RED);
                                btLogistica.setText(quantidade + "");
                                bundle.putInt("bundle_logistica", codTile);
                            }
                        }
                        if (cod == 134) {
                            if (quantidade >= 0) {
                                btComercio.setTextColor(Color.RED);
                                btComercio.setText(quantidade + "");
                                bundle.putInt("bundle_comercio", codTile);
                            }
                        }
                        if (cod == 135) {
                            if (quantidade >= 0) {
                                btGestao.setTextColor(Color.RED);
                                btGestao.setText(quantidade + "");
                                bundle.putInt("bundle_gestao", codTile);
                            }
                        }
                        if (cod == 136) {
                            if (quantidade >= 0) {
                                btInventario.setTextColor(Color.RED);
                                btInventario.setText(quantidade + "");
                                bundle.putInt("bundle_inventario", codTile);
                            }
                        }

                        if (cod == 137) {
                            if (quantidade >= 0) {
                                btPerda.setTextColor(Color.RED);
                                btPerda.setText(quantidade + "");
                                bundle.putInt("bundle_perda", codTile);
                            }
                        }
                    }
                } else {
                    //Set Error message
                    Toast.makeText(getApplicationContext(), "Falha", Toast.LENGTH_SHORT).show();
                }
                //Error status is true
            } else {
                Toast.makeText(getApplicationContext(), "Erro invocando o WebService", Toast.LENGTH_SHORT).show();
            }
            //Re-initialize Error Status to False
            errored = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        //Mensagem de Bem-Vindo ao usuário
        TextView tv = ((TextView) findViewById(R.id.tvWelcome));
        tv.setText("Bem-Vindo, " + sharedpreferences.getString("nome", ""));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btSair:

                //Deslogar
                SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear().commit();

                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void temporizador_30_30(final AsyncEventoWS task) {
        final long TEMPO = 200000; // atualiza o serviço a cada 3,30 minutos
        Log.w("inicio", "inicio");
        Timer timer = null;
        task.execute();
        if (timer == null) {
            timer = new Timer();
            TimerTask tarefa = new TimerTask() {
                public void run() {
                    try {
                        //chamar metodo
                        task.execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);
        }
    }
}
