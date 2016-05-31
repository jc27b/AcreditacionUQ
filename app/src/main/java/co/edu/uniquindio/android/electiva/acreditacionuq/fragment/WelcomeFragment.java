package co.edu.uniquindio.android.electiva.acreditacionuq.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.activity.GameActivity;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    public final static String USUARIO = "Usuario";

    private Usuario usuario;

    public WelcomeFragment() {
        // Required empty public constructor
    }


    public static WelcomeFragment newInstance(Usuario usuario) {

        Bundle args = new Bundle();
        args.putParcelable(USUARIO, usuario);

        WelcomeFragment fragment = new WelcomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.usuario = (getArguments() != null) ? (Usuario) getArguments().getParcelable(USUARIO) : null;

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        TextView txtBienvenido = (TextView) view.findViewById(R.id.text_bienvenido);
        txtBienvenido.setText(getContext().getResources().getString(R.string.bienvenido) + " " + usuario.getNombre() + "!");

        Button btnJugar = (Button) view.findViewById(R.id.btn_jugar);
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GameActivity.class);
                intent.putExtra(USUARIO, usuario);
                startActivity(intent);
            }
        });

        Button btnCambiarUsuario = (Button) view.findViewById(R.id.btn_cambiar_usuario);
        btnCambiarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Falta implementar", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
