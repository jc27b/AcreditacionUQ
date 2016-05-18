package co.edu.uniquindio.android.electiva.acreditacionuq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Juan Camilo on 17/05/2016.
 */
public class Question implements Parcelable {

    private String _id;
    private String enunciado;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private int correcta;
    private String ayuda;

    public Question(String enunciado, String opcion1, String opcion2, String opcion3, String opcion4, int correcta, String ayuda) {
        this.enunciado = enunciado;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.correcta = correcta;
        this.ayuda = ayuda;
    }

    public Question(String _id, String enunciado, String opcion1, String opcion2, int correcta, String ayuda, String opcion4, String opcion3) {
        this._id = _id;
        this.enunciado = enunciado;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.correcta = correcta;
        this.ayuda = ayuda;
        this.opcion4 = opcion4;
        this.opcion3 = opcion3;
    }

    protected Question(Parcel in) {
        _id = in.readString();
        enunciado = in.readString();
        opcion1 = in.readString();
        opcion2 = in.readString();
        opcion3 = in.readString();
        opcion4 = in.readString();
        correcta = in.readInt();
        ayuda = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(enunciado);
        dest.writeString(opcion1);
        dest.writeString(opcion2);
        dest.writeString(opcion3);
        dest.writeString(opcion4);
        dest.writeInt(correcta);
        dest.writeString(ayuda);
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

    public String getAyuda() {
        return ayuda;
    }

    public void setAyuda(String ayuda) {
        this.ayuda = ayuda;
    }


}
