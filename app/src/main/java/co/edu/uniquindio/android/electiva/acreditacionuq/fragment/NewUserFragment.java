package co.edu.uniquindio.android.electiva.acreditacionuq.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.activity.StartActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewUserFragment extends Fragment {

    private EditText editNombre;
    private EditText editCorreo;
    private Button btnAceptar;

    private OnNuevoUsuarioListener listener;

    public NewUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_user, container, false);

        editNombre = (EditText) view.findViewById(R.id.edit_nombre);
        editCorreo = (EditText) view.findViewById(R.id.edit_correo);

        btnAceptar = (Button) view.findViewById(R.id.btn_aceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String correo = editCorreo.getText().toString();
                StartActivity.crudsql.insertarUsuario(nombre, correo, "Si");
                listener.onNuevoUsuario();
            }
        });

        return view;
    }


    /**
     * Interfaz que sera implementada por la actividad para
     * compartir datos con otro fragmento.
     */
    public interface OnNuevoUsuarioListener {
        void onNuevoUsuario();
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
                listener = (OnNuevoUsuarioListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnNuevoUsuarioListener");
            }
        }

    }

}
