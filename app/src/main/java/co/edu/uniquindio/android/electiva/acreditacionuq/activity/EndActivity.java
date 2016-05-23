package co.edu.uniquindio.android.electiva.acreditacionuq.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import co.edu.uniquindio.android.electiva.acreditacionuq.R;
import co.edu.uniquindio.android.electiva.acreditacionuq.fragment.WelcomeFragment;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

public class EndActivity extends AppCompatActivity {

    private TextView txtMensaje;
    private Button btnVolver;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        GameActivity.gameActivity.finish();
        usuario = getIntent().getExtras().getParcelable(WelcomeFragment.USUARIO);

        txtMensaje = (TextView) findViewById(R.id.text_mensaje);
        txtMensaje.setText(getIntent().getExtras().getString(GameActivity.MENSAJE));

        btnVolver = (Button) findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}
