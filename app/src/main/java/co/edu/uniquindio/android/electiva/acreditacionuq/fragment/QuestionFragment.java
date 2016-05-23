package co.edu.uniquindio.android.electiva.acreditacionuq.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Question;

/**
 * Fragmento que será utilizado para mostrar
 * distintos números y colores según se
 * desplace el usuario en el view pager.
 * Los fragmentos serán configurados y
 * mostrados por un adaptador.
 */
public class QuestionFragment extends Fragment implements View.OnClickListener {

    private TextView txtEnunciado;
    private Button btnOpcion1;
    private Button btnOpcion2;
    private Button btnOpcion3;
    private Button btnOpcion4;
    private static final String PREGUNTA = "pregunta";
    private Question pregunta;
    private OnPreguntaRespondidaListener listener;

    /**
     * Constructor por defecto del fragmento
     * el cual es necesario que sea publico
     * y vacio para instanciarlo
     */
    public QuestionFragment() {

    }

    /**
     * Metodo que permite crear una instacia del fragmento, a la cual
     * se le manda como argumentos el indice y el color de fondo
     * que debería usar en un bundle, luego se establece que la
     * instancia del fragmento se conservará aún cuando la actividad
     * sea reconstruida para que no se pierdan los datos, y finalmente
     * se muestra un mensaje en el logat con el indice que le toco
     * a la instancia del fragmento
     * permite copiar un fragmento con diferente color en una posicion distinta
     * @param pregunta Pregunta cuya información mostrará el fragmento
     * @return Instancia del fragmento que será utilizada en la actividad según el adaptador
     */
    public static QuestionFragment newInstance(Question pregunta) {

        QuestionFragment fragment = new QuestionFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(PREGUNTA, pregunta);
        fragment.setArguments(bundle);
        fragment.setRetainInstance(true);

        return fragment;

    }

    /**
     * Metodo llamado cuando se crea el fragmento,
     * el cual da valor a los atributos de clase
     * poniendo lo que llega por argumentos, o si
     * éstos son nulos se pone un valor por defecto
     * @param savedInstanceState Instancia guardada para restaurar datos.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.pregunta = getArguments().getParcelable(PREGUNTA);

    }

    /**
     * Método que crea la vista que utilizará el fragmento,
     * le asigna un texto según la posición del fragmento
     * al título de la vista, y también le asigna
     * un color de background.
     * @param inflater Objeto que permitirá inflar la vista para el fragmento
     * @param container Contenedor según la jerarquía de vistas de la actividad
     * @param savedInstanceState Instancia guardada para restaurar los datos
     * @return Vista que usará el fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_question, container, false);
        txtEnunciado = (TextView) vista.findViewById(R.id.text_enunciado);
        txtEnunciado.setText(pregunta.getEnunciado());
        btnOpcion1 = (Button) vista.findViewById(R.id.text_opcion_1);
        btnOpcion1.setText(pregunta.getOpcion1());
        btnOpcion1.setOnClickListener(this);
        btnOpcion2 = (Button) vista.findViewById(R.id.text_opcion_2);
        btnOpcion2.setText(pregunta.getOpcion2());
        btnOpcion2.setOnClickListener(this);
        btnOpcion3 = (Button) vista.findViewById(R.id.text_opcion_3);
        btnOpcion3.setText(pregunta.getOpcion3());
        btnOpcion3.setOnClickListener(this);
        btnOpcion4 = (Button) vista.findViewById(R.id.text_opcion_4);
        btnOpcion4.setText(pregunta.getOpcion4());
        btnOpcion4.setOnClickListener(this);
        return vista;
    }

    @Override
    public void onClick(View v) {
        int presionada = 0;
        if (v.getId() == btnOpcion1.getId()) {
            presionada = 1;
        } else if (v.getId() == btnOpcion2.getId()) {
            presionada = 2;
        } else if (v.getId() == btnOpcion3.getId()) {
            presionada = 3;
        } else if (v.getId() == btnOpcion4.getId()) {
            presionada = 4;
        }

        if (presionada == pregunta.getCorrecta()) {
            listener.onPreguntaRespondida();
        } else {
            listener.onJuegoTerminado();
        }
    }

    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnPreguntaRespondidaListener {
        void onPreguntaRespondida();
        void onJuegoTerminado();
    }

    /**
     * Metodo llamado cuando se va a agregar el fragmento dentro
     * de la actividad, es utilizado en este caso para confirmar que
     * la actividad implemente una interfaz para pasar datos
     * a otro fragmento.
     * @param context Actividad del fragmento.
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                listener = (OnPreguntaRespondidaListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnPreguntaRespondidaListener");
            }
        }

    }

}