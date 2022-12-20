package Classes;

public class EstadoFunc {
    private String nomefunc;
    private String estado;

    public EstadoFunc(){
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNomefunc(String nomefunc) {
        this.nomefunc = nomefunc;
    }

    public String getEstado() {
        return estado;
    }

    public String getNomefunc() {
        return nomefunc;
    }
}
