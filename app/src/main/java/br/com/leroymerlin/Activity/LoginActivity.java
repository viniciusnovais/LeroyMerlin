package br.com.leroymerlin.Activity;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import br.com.leroymerlin.R;
import br.com.leroymerlin.WebService.WebServiceSoapLogin;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView, mPasswordView;
    public static boolean errored;
    private SoapObject response = null;
    public final static String PREF_NAME = "LoginPreference";
    private String stringTextEmail = "", stringTextPassword = "";
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);

        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if (preferences != null) {
            stringTextEmail = preferences.getString("login", "");
            stringTextPassword = preferences.getString("senha", "");
            if (stringTextEmail != "" && stringTextPassword != "") {
                AsyncCallWS task = new AsyncCallWS();
                //Call execute
                task.execute();
            }
        }


        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                stringTextEmail = mEmailView.getEditableText().toString();
                stringTextPassword = mPasswordView.getEditableText().toString();
                if (stringTextEmail.length() != 0 && stringTextEmail.toString() != "") {
                    if (stringTextPassword.length() != 0 && stringTextPassword.toString() != "") {

                        mEmailView.setText("");
                        mPasswordView.setText("");

                        //Create instance for AsyncCallWS
                        AsyncCallWS task = new AsyncCallWS();
                        //Call execute
                        task.execute();
                    }
                    //If Password text control is empty
                    else {
                        Toast.makeText(getApplicationContext(), "Por favor, coloque a senha", Toast.LENGTH_SHORT).show();
                    }
                    //If Username text control is empty
                } else {
                    Toast.makeText(getApplicationContext(), "Por Favor, coloquqe a senha", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private class AsyncCallWS extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            //Call Web Method
            response = WebServiceSoapLogin.invokeLoginWS(stringTextEmail, stringTextPassword);

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            //Make Progress Bar invisible
            Intent intObj = new Intent(LoginActivity.this, HomeActivity.class);
            //Error status is false
            if (!errored) {
                //Based on Boolean value returned from WebService
                if (Integer.parseInt(response.getProperty("Codigo").toString()) > 0) {

                    //Navigate to Home Screen
                    SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("login", stringTextEmail);
                    editor.putString("senha", stringTextPassword);
                    editor.putString("nome", response.getProperty("Nome").toString());
                    editor.putInt("codUsuario", Integer.parseInt(response.getProperty("Codigo").toString()));
                    editor.putInt("codFilial", Integer.parseInt(response.getProperty("CodigoFilial").toString()));
                    editor.commit();

                    startActivity(intObj);
                } else {
                    //Set Error message
                    Toast.makeText(getApplicationContext(), "Falha no Login, Usuário ou Senha Incorretos", Toast.LENGTH_SHORT).show();
                }
                //Error status is true
            } else {
                Toast.makeText(getApplicationContext(), "Erro invocando o WebService", Toast.LENGTH_SHORT).show();
            }
            //Re-initialize Error Status to False
            errored = false;
        }
    }


}

