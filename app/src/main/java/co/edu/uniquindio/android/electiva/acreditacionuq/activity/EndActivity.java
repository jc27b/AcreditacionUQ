package co.edu.uniquindio.android.electiva.acreditacionuq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.WelcomeFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

public class EndActivity extends AppCompatActivity {

    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        GameActivity.gameActivity.finish();
        usuario = getIntent().getExtras().getParcelable(WelcomeFragment.USUARIO);


    }



}
