package com.appInvernadero.control.business;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.appInvernadero.vo.LoginResponseVO;
import com.google.gson.Gson;

/**
 * Created on 01/08/2017.
 */


public class PermisoUsuarioActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button botonIngresar  = (Button) findViewById(R.id.button);


        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login(){
        Conexion tarea = new Conexion(this);
        String usuario = ((EditText) findViewById(R.id.editText)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText2)).getText().toString();

        tarea.execute(usuario, password);
    }

    public class Conexion extends AsyncTask<String,Integer,LoginResponseVO> {

        private Context context;


        /*$data=array(
		'product_name' =>'Television',
                'price' => 1000,
                'quantity' => 10,
                'seller' =>'XYZ Traders'
        );*/

        public Conexion(Context context){
            this.context=context;
        }

        protected LoginResponseVO doInBackground(String... params) {
            String result = "";

            String usuario = params[0];
            String password = params[1];
            LoginResponseVO loginResponse = null;

            URL url = null;
            try
            {
                //Se establece la URL
                url = new URL("http://52.14.237.8:8282/SistemaCentralizado_IoT/autenticacion/login?usuario="+usuario+"&password="+password);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            // Obtener la conexión
            HttpURLConnection con = null;

            try {
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                //con.setConnectTimeout( 15000);
                //con.setReadTimeout( 10000);
                con.setRequestProperty("Content-Type", "application/json");
                //Activar envío y recepción de datos
                con.setDoInput(true);
                con.setDoOutput(false);


                int resp = con.getResponseCode();
                System.out.print("RESPUESTA #################"+resp);

                Gson gson = new Gson();



                if(resp == HttpURLConnection.HTTP_OK)
                {
                    InputStream inputStreamResponse = con.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStreamResponse, "UTF-8"));
                    result = bufferedReader.readLine();
                    loginResponse  =  gson.fromJson(result, LoginResponseVO.class);

                }
                else
                {
                    result = "error";
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (con != null)
                {
                    con.disconnect();
                }
            }
            return loginResponse;
        }

        protected void onPostExecute(LoginResponseVO loginResponseVO)
        {
            if(loginResponseVO.getCodigo() == 0){
                Intent pantallaPrincipal = new Intent(PermisoUsuarioActivity.this, Principal.class);

                startActivity(pantallaPrincipal);
            }
            else{
                Toast.makeText(getApplicationContext(), "Usuario/Password Incorrecto", Toast.LENGTH_SHORT).show();
            }

        }
    }

}

