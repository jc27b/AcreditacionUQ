package co.edu.uniquindio.android.electiva.acreditacionuq.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.QuestionFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.WelcomeFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.util.AdaptadorDePagerFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.util.Utilidades;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Question;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

public class GameActivity extends AppCompatActivity implements QuestionFragment.OnPreguntaRespondidaListener {

    public final static String MENSAJE = "Mensaje";

    private ViewPager viewPager;
    private ArrayList<Question> preguntas;
    private int posicion;
    private Usuario usuario;
    public static GameActivity gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        posicion = 0;
        usuario = (Usuario) getIntent().getExtras().getParcelable(WelcomeFragment.USUARIO);
        gameActivity = this;

        HiloSecundario hiloSecundario = new HiloSecundario(this);
        hiloSecundario.execute(Utilidades.LISTAR_PREGUNTAS);
    }

    @Override
    public void onPreguntaRespondida() {
        if (posicion != preguntas.size()-1) {
            posicion = posicion + 1;
            viewPager.setCurrentItem(posicion);

            QuestionFragment page = (QuestionFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
            page.countDownTimer.start();

        } else {
            Intent intent = new Intent(GameActivity.this, EndActivity.class);
            intent.putExtra(WelcomeFragment.USUARIO, usuario);
            intent.putExtra(MENSAJE, getBaseContext().getResources().getString(R.string.juego_ganado));
            startActivity(intent);
        }

    }

    @Override
    public void onJuegoTerminado(String mensaje) {
        Intent intent = new Intent(GameActivity.this, EndActivity.class);
        intent.putExtra(WelcomeFragment.USUARIO, usuario);
        intent.putExtra(MENSAJE, mensaje);
        startActivity(intent);
    }

    /**
     * Clase que implementa un hilo secundario para realizar
     * operaciones con con servicios.
     */
    public class HiloSecundario extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progress;
        private Context context;
        private Question pregunta;

        /**
         * Constructor del hilo secundario, que
         * inicializa el contexto y la pelicula.
         * @param context Contexto de la aplicación.
         */
        public HiloSecundario(Context context) {
            this.context = context;
            pregunta = null;
        }

        /**
         * Metodo ejecutado antes de que
         * se ejecute el hilo, muestra un
         * mensaje informativo.
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = ProgressDialog.show(context, context.getString(R.string.cargando_preguntas), context.getString(R.string.espere), true);
        }

        /**
         * Metodo que se ejecuta en el hilo secundario,
         * el cual permite listar las películas y
         * tambien agregarlas.
         * @param params Operación a realizar.
         * @return Operación realizada.
         */
        @Override
        protected Integer doInBackground(Integer... params) {
            if (params[0] == Utilidades.LISTAR_PREGUNTAS) {
                setPreguntas(Utilidades.getListaDePreguntas());
            }

            return params[0];
        }

        /**
         * Metodo ejecutado después de que
         * el hilo finalizó su ejecución.
         * Pone el los controles gráficos
         * la información extraída del servicio.
         * @param integer Operación a realizar.
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);

            if (integer == Utilidades.LISTAR_PREGUNTAS) {

                // implementar preguntas aleatorias pendiente!!!

                AdaptadorDePagerFragment adapter = new AdaptadorDePagerFragment(getSupportFragmentManager(), preguntas);
                viewPager = (ViewPager) findViewById(R.id.viewpager);
                viewPager.setAdapter(adapter);
                viewPager.beginFakeDrag();

                QuestionFragment page = (QuestionFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
                page.countDownTimer.start();

            }

            progress.dismiss();
        }

        public Question getPregunta() {
            return pregunta;
        }

        public void setPregunta(Question pregunta) {
            this.pregunta = pregunta;
        }
    }

    public ArrayList<Question> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Question> preguntas) {
        this.preguntas = preguntas;
    }

}
