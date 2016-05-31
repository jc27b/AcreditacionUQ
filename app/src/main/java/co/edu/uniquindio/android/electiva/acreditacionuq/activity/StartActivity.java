package co.edu.uniquindio.android.electiva.acreditacionuq.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.NewUserFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.WelcomeFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.util.CRUDSQL;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

public class StartActivity extends AppCompatActivity implements NewUserFragment.OnNuevoUsuarioListener {

    public static CRUDSQL crudsql;
    private ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        crudsql = new CRUDSQL(this, 1);
        usuarios = crudsql.getUsuarios();
        Usuario usuarioActual = getUsuarioActual();

        if (usuarioActual == null) {
            NewUserFragment newUserFragment = new NewUserFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.start_fragment, newUserFragment, "newUserFragment");
            fragmentTransaction.commit();
        } else {
            WelcomeFragment welcomeFragment = WelcomeFragment.newInstance(usuarioActual);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.start_fragment, welcomeFragment, "welcomeFragment");
            fragmentTransaction.commit();
        }

    }


    private Usuario getUsuarioActual() {
        for (Usuario usuario: usuarios) {
            if (usuario.getActual().equals("Si")) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void onNuevoUsuario() {
        usuarios = crudsql.getUsuarios();
        Usuario usuarioActual = getUsuarioActual();
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance(usuarioActual);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.start_fragment, welcomeFragment, "welcomeFragment");
        fragmentTransaction.commit();
    }


}
