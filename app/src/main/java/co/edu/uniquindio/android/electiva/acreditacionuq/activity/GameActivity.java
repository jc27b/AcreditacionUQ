package co.edu.uniquindio.android.electiva.acreditacionuq.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
    public final static String PUNTAJE = "Puntaje";

    private ViewPager viewPager;
    private ArrayList<Question> preguntas;
    private int posicion;
    private Usuario usuario;
    public static GameActivity gameActivity;

    private int puntaje;
    private int pregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        posicion = 0;
        usuario = (Usuario) getIntent().getExtras().getParcelable(WelcomeFragment.USUARIO);
        gameActivity = this;

        puntaje = 0;
        pregunta = 1;

        HiloSecundario hiloSecundario = new HiloSecundario(this);
        hiloSecundario.execute(Utilidades.LISTAR_PREGUNTAS);




    }
    /**
     * Este metodo verifica si la conexion a internet esta disponible,
     * si lo esta devuelve un true de lo contrario un false.
     *
     * @param ctx
     * @return true o false segun sea el estado de l conexion
     */
    public static boolean verificaConexion(Context ctx) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < 2; i++) {

            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }
    @Override
    public void onPreguntaRespondida() {
        if (posicion != preguntas.size()-1) {
            posicion = posicion + 1;
            viewPager.setCurrentItem(posicion);

            QuestionFragment page = (QuestionFragment) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
            page.countDownTimer.start();

            puntaje = (int) Math.pow(2, pregunta);
            pregunta ++;

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
        intent.putExtra(PUNTAJE, puntaje);
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

                ArrayList<Question> preguntasAleatorias = new ArrayList<Question>();
                ArrayList<Integer> randoms = new ArrayList<Integer>();
                for (int i = 0; i < 10; i++) {
                    int random = (int) (Math.random()*40);
                    if (!randoms.contains(random)) {
                        preguntasAleatorias.add(preguntas.get(random));
                    } else {
                        i --;
                    }
                }

                setPreguntas(preguntasAleatorias);

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














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.llamada) {


            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+3123132829"));
            startActivity(intent);
        }
        if (id == R.id.s0_s0) {
            Toast toast2 =
                    Toast.makeText(getApplicationContext(),
                            "mensaje toast 2", Toast.LENGTH_SHORT);
            toast2.show();
        }

        return super.onOptionsItemSelected(item);
    }




}
