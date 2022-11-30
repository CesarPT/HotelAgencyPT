package Classes;

public class Cliente {
    int idUtilizador, idCliente;

    /**
     * Contrutor completo
     *
     * @param idUtilizador
     * @param idCliente
     */
    public Cliente(int idUtilizador, int idCliente) {
        this.idUtilizador = idUtilizador;
        this.idCliente = idCliente;
    }

    public Cliente() {

    }

    /**
     * Devolve:
     *
     * @return idUtilizador
     */
    public int getIdUtilizador() {
        return idUtilizador;
    }

    /**
     * Muda o valor:
     *
     * @param idUtilizador set
     */
    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }

    /**
     * Devolve:
     *
     * @return idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Muda o valor:
     *
     * @param idCliente set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
