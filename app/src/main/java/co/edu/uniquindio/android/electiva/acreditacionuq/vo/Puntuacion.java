package co.edu.uniquindio.android.electiva.acreditacionuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juan Camilo on 22/05/2016.
 */
public class Puntuacion implements Parcelable {

    private String _id;
    private String usuario;
    private int puntaje;

    public Puntuacion(String _id, String usuario, int puntaje) {
        this._id = _id;
        this.puntaje = puntaje;
        this.usuario = usuario;
    }

    protected Puntuacion(Parcel in) {
        _id = in.readString();
        usuario = in.readString();
        puntaje = in.readInt();
    }

    public static final Creator<Puntuacion> CREATOR = new Creator<Puntuacion>() {
        @Override
        public Puntuacion createFromParcel(Parcel in) {
            return new Puntuacion(in);
        }

        @Override
        public Puntuacion[] newArray(int size) {
            return new Puntuacion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(usuario);
        dest.writeInt(puntaje);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

}
