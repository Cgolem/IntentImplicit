package com.example.intentimplicit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNavegador;
    Button btnGMaps;
    Button btnLlamada;
    Button btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNavegador = (Button) findViewById(R.id.btnNavegador);
        btnGMaps = (Button) findViewById(R.id.btnGM);
        btnLlamada = (Button) findViewById(R.id.btnLlamada);
        btnEmail = (Button) findViewById(R.id.btnEmail);
    }

    /*
    * @description: Método encargado de abrir el navegador
    * */
    public void abreNavegador(View view) {
        Intent abrirNavegador = new Intent(Intent.ACTION_VIEW);

        //Asignamos la URL en formato String a nuestra intención.
        abrirNavegador.setData(Uri.parse("https://www.youtube.com/watch?v=5rhT1c_PiqM"));

        //Verificamos si el intent puede ser atendida por el sistema
        if( abrirNavegador.resolveActivity((getPackageManager())) != null ){
            startActivity(abrirNavegador);
        } else {
            // Podemos agregar una condigicón extra, como mensaje en pantalla tipo Alerta
        }
    }

    public void abreMaps(View view) {
        Intent abrirMaps = new Intent(Intent.ACTION_VIEW);

        //Asignamos la posición de un punto (latitud y longitud)
        //También podemos indicar con que zoom se abrirá en maps
        abrirMaps.setData(Uri.parse("geo:19.331700,-99.191470?zoom=17"));

        startActivity(abrirMaps);
    }

    public void abreLlamada(View view) {
        Intent hacerLlamada = new Intent(Intent.ACTION_CALL);

        //Asignamos un número telefónico válido
        hacerLlamada.setData(Uri.parse("tel:5555555555"));

        if( hacerLlamada.resolveActivity(getPackageManager()) != null ) {
            startActivity(hacerLlamada);
        }
    }

    public void abreEnviarEmail(View view) {
        try {
            Intent abrirCorreo = new Intent(Intent.ACTION_SEND);

            abrirCorreo.setData(Uri.parse("mailto:"));

            //Indicamos el tipo de formato, para este caso es texto plano
            abrirCorreo.setType("plain/text");

            //Asignamos el correo destino
            abrirCorreo.putExtra(Intent.EXTRA_EMAIL,"correo@correo.com");

            //Asignamos el nombre del asunto
            abrirCorreo.putExtra(Intent.EXTRA_SUBJECT,"Correo de prueba");

            //Asignamos texto al cuerpo del correo
            abrirCorreo.putExtra(Intent.EXTRA_TEXT,"Contenido del correo");

            //Iniciamos la intención.
            startActivity(abrirCorreo);
        } catch(Exception error) {
            Log.e("IntentImplicito", "Prueba: envio de email", error);
        }
    }
}
