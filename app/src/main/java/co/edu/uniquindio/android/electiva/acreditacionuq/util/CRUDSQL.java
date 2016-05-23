package co.edu.uniquindio.android.electiva.acreditacionuq.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Puntuacion;
import co.edu.uniquindio.android.electiva.acreditacionuq.vo.Usuario;

/**
 * Created by Juan Camilo on 22/05/2016.
 */
public class CRUDSQL {

    private SQLiteHelper usdbh;
    private SQLiteDatabase db;

    public CRUDSQL(Context context, int version) {
        usdbh = new SQLiteHelper(context, Utilidades.NOMBRE_BD , null, version);
        db = usdbh.getWritableDatabase();
    }

    public static String crearTablaUsuario() {

        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? TEXT, ? TEXT)";

        StringBuilder builder = new StringBuilder(crearTabla);

        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_1);

        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_1[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_1[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_1[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_1[3]);

        return builder.toString();
    }

    public static String crearTablaPuntuacion() {

        String crearTabla = "CREATE TABLE ? ( ? INTEGER PRIMARY KEY AUTOINCREMENT, ? TEXT, ? INT)";

        StringBuilder builder = new StringBuilder(crearTabla);

        builder.replace(builder.indexOf("?"), crearTabla.indexOf("?")+1,Utilidades.NOMBRE_TABLA_2);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_2[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_2[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?")+1,Utilidades.CAMPOS_TABLA_2[2]);

        return builder.toString();
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Cursor c = db.query(Utilidades.NOMBRE_TABLA_1, Utilidades.CAMPOS_TABLA_1, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String _id = c.getString(0);
                String nombre = c.getString(1);
                String correo = c.getString(2);
                String actual = c.getString(3);
                usuarios.add(new Usuario(_id, nombre, correo, actual));
            } while (c.moveToNext());
        }
        return usuarios;
    }

    public ArrayList<Puntuacion> getPuntuaciones() {
        ArrayList<Puntuacion> puntuaciones = new ArrayList<>();
        Cursor c = db.query(Utilidades.NOMBRE_TABLA_1, Utilidades.CAMPOS_TABLA_2, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                String _id = c.getString(0);
                String usuario = c.getString(1);
                int puntaje = c.getInt(2);
                puntuaciones.add(new Puntuacion(_id, usuario, puntaje));
            } while (c.moveToNext());
        }
        return puntuaciones;
    }

    public void insertarUsuario(String... campos) {
        String insertar = "INSERT INTO ? (?,?,?) VALUES ( '?', '?', '?' )";
        StringBuilder builder = new StringBuilder(insertar);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_1);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_1[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_1[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_1[3]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, campos[2]);
        ejecutarConsulta(builder.toString());
    }

    public void insertarPuntuacion(String usuario, int puntaje) {
        String insertar = "INSERT INTO ? (?,?) VALUES ( '?', '?' )";
        StringBuilder builder = new StringBuilder(insertar);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_2);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_2[1]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_2[2]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, usuario);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, ""+puntaje);
        ejecutarConsulta(builder.toString());
    }

    public void elimarUsuario(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_1);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_1[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }

    public void elimarPuntuacion(String idP) {
        StringBuilder builder = new StringBuilder("DELETE FROM ? WHERE ?= '?'");
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.NOMBRE_TABLA_2);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, Utilidades.CAMPOS_TABLA_2[0]);
        builder.replace(builder.indexOf("?"), builder.indexOf("?") + 1, idP);
        ejecutarConsulta(builder.toString());
    }

    private void ejecutarConsulta(String consulta) {
        db.execSQL(consulta);
    }

}
