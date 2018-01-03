package br.com.leroymerlin.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ListIterator;

import br.com.leroymerlin.Adapter.ListaJustificativaAssistenciaTecnicaAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaCancelamentoAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaDemarcaAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaDescontoAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaFaturamentoAdapter;
import br.com.leroymerlin.Adapter.ListaJustificativaMargemNegativaAdapter;
import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapAssistenciaTecnica;
import br.com.leroymerlin.WebService.WebServiceSoapCancelamento;
import br.com.leroymerlin.WebService.WebServiceSoapDemarcaConhecida;
import br.com.leroymerlin.WebService.WebServiceSoapDesconto;
import br.com.leroymerlin.WebService.WebServiceSoapFaturamentoPendente;
import br.com.leroymerlin.WebService.WebServiceSoapMargemNegativa;
import br.com.leroymerlin.dao.AssistenciaTecnicaDao;
import br.com.leroymerlin.dao.CancelamentoDao;
import br.com.leroymerlin.dao.DemarcaConhecidaDao;
import br.com.leroymerlin.dao.DescontoDao;
import br.com.leroymerlin.dao.FaturamentoPendenteDao;
import br.com.leroymerlin.dao.MargemNegativaDao;
import br.com.leroymerlin.model.AssistenciaTecnica;
import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.DemarcaConhecida;
import br.com.leroymerlin.model.Desconto;
import br.com.leroymerlin.model.FaturamentoPendente;
import br.com.leroymerlin.model.MargemNegativa;

/**
 * Created by PDA on 05/09/2017.
 */

public class ListaActivity extends AppCompatActivity {

    //lists
    private List<Desconto> lista;
    private List<Cancelamento> cancelamentoList;
    private List<FaturamentoPendente> faturamentoPendenteList;
    private List<DemarcaConhecida> demarcaConhecidaList;
    private List<MargemNegativa> margemNegativas;
    private List<AssistenciaTecnica> assistenciaTecnicas;
    //modelos
    private Desconto d = new Desconto();
    private Cancelamento c = new Cancelamento();
    private FaturamentoPendente f = new FaturamentoPendente();
    private MargemNegativa m = new MargemNegativa();
    private AssistenciaTecnica a = new AssistenciaTecnica();
    private DemarcaConhecida de = new DemarcaConhecida();
    //adapter
    private ListaJustificativaDescontoAdapter adapter;
    private ListaJustificativaCancelamentoAdapter cancelamentoAdapter;
    private ListaJustificativaFaturamentoAdapter faturamentoAdapter;
    private ListaJustificativaAssistenciaTecnicaAdapter assistenciaTecnicaAdapter;
    private ListaJustificativaMargemNegativaAdapter margemNegativaAdapter;
    private ListaJustificativaDemarcaAdapter demarcaAdapter;
    //dao
    private DescontoDao descontoDao;
    private CancelamentoDao cancelamentoDao;
    private DemarcaConhecidaDao demarcaConhecidaDao;
    private MargemNegativaDao margemNegativaDao;
    private AssistenciaTecnicaDao assistenciaTecnicaDao;
    private FaturamentoPendenteDao faturamentoPendenteDao;
    //recyclerview
    private RecyclerView recyclerView;
    //variaves aleatorias
    private int tipo;
    private String justificativa = "";
    private byte[] imageByte = new byte[0];
    private AlertDialog dialogCamera = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        descontoDao = new DescontoDao(this);
        cancelamentoDao = new CancelamentoDao(this);
        faturamentoPendenteDao = new FaturamentoPendenteDao(this);
        demarcaConhecidaDao = new DemarcaConhecidaDao(this);
        margemNegativaDao = new MargemNegativaDao(this);
        assistenciaTecnicaDao = new AssistenciaTecnicaDao(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        tipo = getIntent().getIntExtra("tipo", -1);

        AsyncLista task = new AsyncLista();
        task.execute();


    }


    public class AsyncLista extends AsyncTask {

        SharedPreferences preferences = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(ListaActivity.this);
            progressDialog.setMessage("Carregando...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            if (tipo == 101) {
                lista = WebServiceSoapDesconto.GetListaDesconto(preferences.getInt("codFilial", -1));
                descontoDao.incluir(lista);
            } else if (tipo == 107) {
                cancelamentoList = WebServiceSoapCancelamento.GetListaCancelamento(preferences.getInt("codFilial", -1));
                cancelamentoDao.incluir(cancelamentoList);

            } else if (tipo == 111) {
                faturamentoPendenteList = WebServiceSoapFaturamentoPendente.GetListaFaturamentoPendente(preferences.getInt("codFilial", -1));
                faturamentoPendenteDao.incluir(faturamentoPendenteList);
            } else if (tipo == 601) {
                demarcaConhecidaList = WebServiceSoapDemarcaConhecida.GetMargemConhecida(preferences.getInt("codFilial", -1));
                demarcaConhecidaDao.incluir(demarcaConhecidaList);
            } else if (tipo == 406) {
                margemNegativas = WebServiceSoapMargemNegativa.GetMargemNegativa(preferences.getInt("codFilial", -1));
                margemNegativaDao.incluir(margemNegativas);
            } else if (tipo == 305) {
                assistenciaTecnicas = WebServiceSoapAssistenciaTecnica.GetAssistenciaTecnica(preferences.getInt("codFilial", -1));
                assistenciaTecnicaDao.incluir(assistenciaTecnicas);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (progressDialog.isShowing()) {
                if (tipo == 101) {
                    adapter = new ListaJustificativaDescontoAdapter(ListaActivity.this, descontoDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClick(new ListaJustificativaDescontoAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });
                } else if (tipo == 107) {
                    cancelamentoAdapter = new ListaJustificativaCancelamentoAdapter(ListaActivity.this, cancelamentoDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(cancelamentoAdapter);

                    cancelamentoAdapter.setOnItemClick(new ListaJustificativaCancelamentoAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });
                } else if (tipo == 111) {
                    faturamentoAdapter = new ListaJustificativaFaturamentoAdapter(ListaActivity.this, faturamentoPendenteDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(faturamentoAdapter);
                    faturamentoAdapter.setOnItemClick(new ListaJustificativaFaturamentoAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });
                } else if (tipo == 601) {
                    demarcaAdapter = new ListaJustificativaDemarcaAdapter(ListaActivity.this, demarcaConhecidaDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(demarcaAdapter);
                    demarcaAdapter.setOnItemClick(new ListaJustificativaCancelamentoAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });

                } else if (tipo == 406) {
                    margemNegativaAdapter = new ListaJustificativaMargemNegativaAdapter(ListaActivity.this, margemNegativaDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(margemNegativaAdapter);
                    margemNegativaAdapter.setOnItemClick(new ListaJustificativaMargemNegativaAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });
                } else if (tipo == 305) {
                    assistenciaTecnicaAdapter = new ListaJustificativaAssistenciaTecnicaAdapter(ListaActivity.this, assistenciaTecnicaDao.listar(preferences.getInt("codFilial", -1)));
                    recyclerView.setAdapter(assistenciaTecnicaAdapter);
                    assistenciaTecnicaAdapter.setOnClickListener(new ListaJustificativaAssistenciaTecnicaAdapter.ItemOnClick() {
                        @Override
                        public void onClick(int position) {
                            popupJustificar(position);
                        }
                    });
                }

                progressDialog.dismiss();
            }
        }
    }

    //melhorar esse metodo para servir para todos os outros
    public class AsyncPostJustificativa extends AsyncTask<Object, Void, Object[]> {


        @Override
        protected Object[] doInBackground(Object... params) {
            boolean resposta = false;
            int codigoTile = Integer.parseInt(params[3].toString());
            if (codigoTile == 101) {
                resposta = WebServiceSoapDesconto.postJustificativa((Desconto) params[0]);
            } else if (codigoTile == 107) {
                resposta = WebServiceSoapCancelamento.postJustificativa((Cancelamento) params[0]);
            } else if (codigoTile == 111) {
                resposta = WebServiceSoapFaturamentoPendente.postJustificativa((FaturamentoPendente) params[0]);
            } else if (codigoTile == 601) {
                resposta = WebServiceSoapDemarcaConhecida.postJustificativa((DemarcaConhecida) params[0]);
            } else if (codigoTile == 406) {
                resposta = WebServiceSoapMargemNegativa.postJustificativa((MargemNegativa) params[0]);
            } else if (codigoTile == 305) {
                resposta = WebServiceSoapAssistenciaTecnica.postJustificativa((AssistenciaTecnica) params[0]);
            }

            //se exportar a resposta, muda a flag para 1
            if (resposta) {
                params[2] = resposta;
                params[3] = codigoTile;
                return params;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object[] objects) {
            super.onPostExecute(objects);

            if (objects != null) {
                if ((boolean) objects[2]) {
                    if ((int) objects[3] == 101) {
                        descontoDao.export((Desconto) objects[0]);
                        //atualizando lista
                        adapter.remove(Integer.parseInt(objects[1].toString()));
                    } else if ((int) objects[3] == 107) {
                        cancelamentoDao.export((Cancelamento) objects[0]);
                        cancelamentoAdapter.remove(Integer.parseInt(objects[1].toString()));
                    } else if ((int) objects[3] == 111) {
                        faturamentoPendenteDao.export((FaturamentoPendente) objects[0]);
                        faturamentoAdapter.remove(Integer.parseInt(objects[1].toString()));
                    } else if ((int) objects[3] == 601) {
                        demarcaConhecidaDao.export((DemarcaConhecida) objects[0]);
                        demarcaAdapter.remove(Integer.parseInt(objects[1].toString()));
                    } else if ((int) objects[3] == 406) {
                        margemNegativaDao.export((MargemNegativa) objects[0]);
                        margemNegativaAdapter.remove(Integer.parseInt(objects[1].toString()));
                    } else if ((int) objects[3] == 305) {
                        assistenciaTecnicaDao.export((AssistenciaTecnica) objects[0]);
                        assistenciaTecnicaAdapter.remove(Integer.parseInt(objects[1].toString()));
                    }

                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_botoes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.btGrafico:
                Intent i = new Intent(ListaActivity.this, GraficoActivity.class);
                i.putExtra("tipo", tipo);
                startActivity(i);
                finish();
                return true;
            case R.id.btLista:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void popupJustificar(final int position) {
        final AlertDialog dialog;
        View v = View.inflate(ListaActivity.this, R.layout.popup_justificativa, null);
        final EditText editJustificar = (EditText) v.findViewById(R.id.editJustificativa);
        final ImageView imageView = (ImageView) v.findViewById(R.id.imageJustificativa);
        Button btEnviar = (Button) v.findViewById(R.id.btEnviarJustificativa);
        AlertDialog.Builder builder = new AlertDialog.Builder(ListaActivity.this);
        builder.setView(v);
        dialog = builder.create();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editJustificar.getEditableText().toString().equals("")) {
                    justificativa = editJustificar.getText().toString();
                    if (tipo == 101) {
                        d = lista.get(position);
                        d.setImagem(imageByte);
                        d.setJustificativa(justificativa);
                        descontoDao.alterarJustificativa(d);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(d, position, false, 101);

                    } else if (tipo == 107) {
                        c = cancelamentoList.get(position);
                        c.setImagem(imageByte);
                        c.setJustificativa(justificativa);
                        cancelamentoDao.alterarJustificativa(c);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(c, position, false, 107);
                    } else if (tipo == 111) {
                        f = faturamentoPendenteList.get(position);
                        f.setImagem(imageByte);
                        f.setJustificativa(justificativa);
                        faturamentoPendenteDao.alterarJustificativa(f);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(f, position, false, 111);
                    } else if (tipo == 601) {
                        de = demarcaConhecidaList.get(position);
                        de.setImagem(imageByte);
                        de.setJustificativa(justificativa);
                        demarcaConhecidaDao.alterarJustificativa(de);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(de, position, false, 601);

                    } else if (tipo == 406) {
                        m = margemNegativas.get(position);
                        m.setImagem(imageByte);
                        m.setJustificativa(justificativa);
                        margemNegativaDao.alterarJustificativa(m);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(m, position, false, 406);
                    } else if (tipo == 305) {
                        a = assistenciaTecnicas.get(position);
                        a.setImagem(imageByte);
                        a.setJustificativa(justificativa);
                        assistenciaTecnicaDao.alterarJustificativa(a);

                        AsyncPostJustificativa task = new AsyncPostJustificativa();
                        task.execute(a, position, false, 305);
                    }
                    dialog.dismiss();
                }
            }
        });

        dialog.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final View view = getLayoutInflater().inflate(R.layout.frame_image_todo, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(view);
        dialogCamera = builder.create();
        ImageView image = (ImageView) view.findViewById(R.id.image);
        Button btSalvar = (Button) view.findViewById(R.id.btEnviarFoto);
        Button btCancelar = (Button) view.findViewById(R.id.btCancelar);

        Bitmap photo, originalBitmap, resizedBitmap;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case 0:
                    photo = (Bitmap) data.getExtras().get("data");
                    originalBitmap = photo;
                    resizedBitmap = Bitmap.createScaledBitmap(
                            originalBitmap, photo.getWidth() * 3, photo.getHeight() * 3, false);

                    imageByte = getBitmapAsByteArray(originalBitmap);
                    image.setImageBitmap(resizedBitmap);

                    dialogCamera.show();

                    break;
            }

        }

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCamera.dismiss();
            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCamera.dismiss();

            }
        });
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
