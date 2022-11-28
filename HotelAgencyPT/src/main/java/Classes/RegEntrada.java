package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class RegEntrada {
    private int numcartao;
    private int idcliente;
    private int idreserva;

    private int idquarto;
    private String data;
    private String hora;
    private String local;

    /**
     * Construtor completo
     * @param numcartao
     * @param idcliente
     * @param idreserva
     * @param idquarto
     * @param local
     * @param data
     * @param hora
     */
    public RegEntrada(int numcartao, int idcliente, int idreserva, int idquarto, String local, String data, String hora) {
        this.numcartao = numcartao;
        this.idcliente = idcliente;
        this.idreserva = idreserva;
        this.idquarto = idquarto;
        this.local = local;
        this.data = data;
        this.hora = hora;
    }

    /**
     * Construtor simples
     */
    public RegEntrada() {
    }

    public int getNumcartao() {
        return numcartao;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public int getIdreserva() {
        return idreserva;
    }
    public int getIdquarto() {
        return idquarto;
    }

    public String getLocal() {
        return local;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public void setNumcartao(int numcartao) {
        this.numcartao = numcartao;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }
    public int setIdquarto() {
        return idquarto;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
