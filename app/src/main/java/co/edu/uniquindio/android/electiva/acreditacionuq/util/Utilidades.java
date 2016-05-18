package co.edu.uniquindio.android.electiva.acreditacionuq.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Question;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by Juan Camilo on 17/05/2016.
 */
public class Utilidades {

    public final static String URL_SERVICIO = "http://acreditacionuq-jc27b.rhcloud.com/pregunta";
    public static final int LISTAR_PREGUNTAS = 1;

    /**
     * Se encarga de converir un String formato JSON a una Pregunta
     * @param jsonPregunta string en formato JSON
     * @return pregunta resultante de la conversión
     */
    public static Question convertirJSONAPregunta(String jsonPregunta)
    {
        Gson gson = new Gson();
        Question pregunta = gson.fromJson(jsonPregunta, Question.class);
        return pregunta;
    }

    /**
     * Se encarga de convertir una pregunta en un JSON
     * @param pregunta pregunta que se desea transformar
     * @return cadena en formato de json de pregunta
     */
    public static String convertirPreguntaAJSON(Question pregunta) {
        Gson gson = new Gson();
        String json = gson.toJson(pregunta);
        return json;
    }

    /**
     * Se encarga de mostrar un mensaje en pantalla
     * @param mensaje mensaje que se quiere enseñar
     */
    public static void mostrarMensaje(String mensaje, Context context){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Se encarga de consumir el listado de preguntas desde el servicio
     * @return Las preguntas alojadas en el servicio
     */
    public static ArrayList<Question> getListaDePreguntas(){

        ArrayList<Question> preguntas = new ArrayList<>();

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(Utilidades.URL_SERVICIO);
        request.setHeader("content-type", "application/json");

        try {

            HttpResponse resp = httpClient.execute(request);
            String respStr = EntityUtils.toString(resp.getEntity());

            Gson gson = new Gson();
            Type tipoListaPreguntas = new TypeToken<ArrayList<Question>>(){}.getType();

            preguntas = gson.fromJson(respStr, tipoListaPreguntas);

        } catch (Exception e) {
            Log.v(Utilidades.class.getSimpleName(), e.getMessage());
            return null;
        }

        return preguntas;
    }

}
