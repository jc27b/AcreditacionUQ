package co.edu.uniquindio.android.electiva.acreditacionuq.vo;

/**
 * Created by David on 17/05/2016.
 */
public class Premio {

    private String premio;
    private boolean actual=false;

    public Premio(String premio, boolean actual) {
        this.premio = premio;
        this.actual = actual;
    }

    public String getPremio() {
        return premio;
    }

    public void setPremio(String premio) {
        this.premio = premio;
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }
    
}
