package sistemacoilfx.modelo.pojo;

public class AreaAcademica {
    
    Integer idAreaAcademica;
    String nombre;
    String clave;

    public AreaAcademica() {}

    public AreaAcademica(Integer idAreaAcademica, String nombre, String clave) {
        this.idAreaAcademica = idAreaAcademica;
        this.nombre = nombre;
        this.clave = clave;
    }

    public Integer getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(Integer idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}