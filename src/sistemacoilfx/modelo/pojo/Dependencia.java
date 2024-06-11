package sistemacoilfx.modelo.pojo;

public class Dependencia {
    
    Integer idDependencia;
    String nombre;
    String campus;
    String municipio;
    String clave;
    Integer idAreaAcademica;

    public Dependencia() {}

    public Dependencia(Integer idDependencia, String nombre, String campus, String municipio, String clave, Integer idAreaAcademica) {
        this.idDependencia = idDependencia;
        this.nombre = nombre;
        this.campus = campus;
        this.municipio = municipio;
        this.clave = clave;
        this.idAreaAcademica = idAreaAcademica;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(Integer idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}