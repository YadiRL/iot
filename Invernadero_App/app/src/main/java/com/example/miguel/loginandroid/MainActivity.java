package com.example.miguel.loginandroid;

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
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;


public class MainActivity{    }

/*
public class MainActivity extends AppCompatActivity {

    Conexion tarea = new Conexion(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button botonIngresar  = (Button) findViewById(R.id.button);

        botonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = ((EditText) findViewById(R.id.editText)).getText().toString();
                String password = ((EditText) findViewById(R.id.editText2)).getText().toString();

                tarea.execute();
                if(usuario.equals("admin") && password.equals("admin")){
                    Intent pantallaPrincipal = new Intent(MainActivity.this, Principal.class);
                    startActivity(pantallaPrincipal);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Usuario/Password Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public class Conexion extends AsyncTask<String,Integer,String> {

        private Context context;


        /*$data=array(
		'product_name' =>'Television',
                'price' => 1000,
                'quantity' => 10,
                'seller' =>'XYZ Traders'
        );*/
/*
        public Conexion(Context context){
            this.context=context;
        }

        protected String doInBackground(String... params) {
            String result = "";

            URL url = null;
            try
            {
                //Se establece la URL
                url = new URL("http://52.14.237.8:8282/SistemaCentralizado_IoT/autenticacion/login?usuario=admin&password=admin");
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
            return result;
        }

        protected void onPostExecute(String result)
        {
            System.out.println(result);

        }
    }

}
*/