package sistemacoilfx.modelo.pojo;

public class Idioma {
    
    Integer idIdioma;
    String nombre;

    public Idioma() {}

    public Idioma(Integer idIdioma, String nombre) {
        this.idIdioma = idIdioma;
        this.nombre = nombre;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}