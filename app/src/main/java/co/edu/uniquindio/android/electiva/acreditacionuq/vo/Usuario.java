package co.edu.uniquindio.android.electiva.acreditacionuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juan Camilo on 22/05/2016.
 */
public class Usuario implements Parcelable {

    private String _id;
    private String nombre;
    private String correo;
    private String actual;

    public Usuario(String _id, String nombre, String correo, String actual) {
        this._id = _id;
        this.nombre = nombre;
        this.correo = correo;
        this.actual = actual;
    }

    protected Usuario(Parcel in) {
        _id = in.readString();
        nombre = in.readString();
        correo = in.readString();
        actual = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(actual);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

}
