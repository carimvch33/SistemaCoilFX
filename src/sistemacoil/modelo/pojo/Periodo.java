package sistemacoil.modelo.pojo;

import java.util.Date;

/**
 *
 * @author jafet
 */
public class Periodo {
    private int idPeriodo;
    private String nombrePeriodo;
    private String anioPeriodo;
    private String inicioPeriodo;
    private String finPeriodo;

    public Periodo() {
    }

    public Periodo(int idPeriodo, String nombre, String anioPeriodo, String inicioPeriodo, String finPeriodo) {
        this.idPeriodo = idPeriodo;
        this.nombrePeriodo = nombre;
        this.anioPeriodo = anioPeriodo;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public String getAnioPeriodo() {
        return anioPeriodo;
    }

    public void setAnioPeriodo(String anioPeriodo) {
        this.anioPeriodo = anioPeriodo;
    }

    public String getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(String inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public String getFinPeriodo() {
        return finPeriodo;
    }

    public void setFinPeriodo(String finPeriodo) {
        this.finPeriodo = finPeriodo;
    }
    
    @Override
    public String toString() {
        return this.nombrePeriodo;
    }
}
