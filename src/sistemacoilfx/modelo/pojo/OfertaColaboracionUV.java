package sistemacoilfx.modelo.pojo;

public class OfertaColaboracionUV {

    Integer idOfertaColaboracionUV;
    
    String nombre;
    String disciplina;
    String objetivoCurso;
    String perfilEstudiante;
    String temaInteres;
    String informacionAdicional;
    String estado;
    Integer idIdioma;
    Integer idAreaAcademica;
    Integer idDependencia;
    Integer idPeriodo;
    
    String Idioma;
    String areaAcademica;
    String dependencia;
    String periodo;
    
    Integer idProfesorUV;

    public OfertaColaboracionUV() {}

    public OfertaColaboracionUV(Integer idOfertaColaboracionUV, String nombre, String disciplina, String objetivoCurso, String perfilEstudiante, String temaInteres, String informacionAdicional, String estado, Integer idIdioma, String Idioma, Integer idAreaAcademica, String areaAcademica, Integer idDependencia, String dependencia, Integer idPeriodo, String periodo, Integer idProfesorUV) {
        this.idOfertaColaboracionUV = idOfertaColaboracionUV;
        this.nombre = nombre;
        this.disciplina = disciplina;
        this.objetivoCurso = objetivoCurso;
        this.perfilEstudiante = perfilEstudiante;
        this.temaInteres = temaInteres;
        this.informacionAdicional = informacionAdicional;
        this.estado = estado;
        this.idIdioma = idIdioma;
        this.Idioma = Idioma;
        this.idAreaAcademica = idAreaAcademica;
        this.areaAcademica = areaAcademica;
        this.idDependencia = idDependencia;
        this.dependencia = dependencia;
        this.idPeriodo = idPeriodo;
        this.periodo = periodo;
        this.idProfesorUV = idProfesorUV;
    }

    public Integer getIdOfertaColaboracionUV() {
        return idOfertaColaboracionUV;
    }

    public void setIdOfertaColaboracionUV(Integer idOfertaColaboracionUV) {
        this.idOfertaColaboracionUV = idOfertaColaboracionUV;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getObjetivoCurso() {
        return objetivoCurso;
    }

    public void setObjetivoCurso(String objetivoCurso) {
        this.objetivoCurso = objetivoCurso;
    }

    public String getPerfilEstudiante() {
        return perfilEstudiante;
    }

    public void setPerfilEstudiante(String perfilEstudiante) {
        this.perfilEstudiante = perfilEstudiante;
    }

    public String getTemaInteres() {
        return temaInteres;
    }

    public void setTemaInteres(String temaInteres) {
        this.temaInteres = temaInteres;
    }

    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String Idioma) {
        this.Idioma = Idioma;
    }

    public Integer getIdAreaAcademica() {
        return idAreaAcademica;
    }

    public void setIdAreaAcademica(Integer idAreaAcademica) {
        this.idAreaAcademica = idAreaAcademica;
    }

    public String getAreaAcademica() {
        return areaAcademica;
    }

    public void setAreaAcademica(String areaAcademica) {
        this.areaAcademica = areaAcademica;
    }

    public Integer getIdDependencia() {
        return idDependencia;
    }

    public void setIdDependencia(Integer idDependencia) {
        this.idDependencia = idDependencia;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Integer getIdProfesorUV() {
        return idProfesorUV;
    }

    public void setIdProfesorUV(Integer idProfesorUV) {
        this.idProfesorUV = idProfesorUV;
    }
}