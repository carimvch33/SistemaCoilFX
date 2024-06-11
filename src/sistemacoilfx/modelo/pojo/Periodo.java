package sistemacoilfx.modelo.pojo;

public class Periodo {
    
    Integer idPeriodo;
    String nombrePeriodo;
    String anioPeriodo;
    String inicioPeriodo;
    String finPeriodo;

    public Periodo() {
    }

    public Periodo(Integer idPeriodo, String nombrePeriodo, String anioPeriodo, String inicioPeriodo, String finPeriodo) {
        this.idPeriodo = idPeriodo;
        this.nombrePeriodo = nombrePeriodo;
        this.anioPeriodo = anioPeriodo;
        this.inicioPeriodo = inicioPeriodo;
        this.finPeriodo = finPeriodo;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
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
    public String toString(){
        return this.nombrePeriodo;
    }
}