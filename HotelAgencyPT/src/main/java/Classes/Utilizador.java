package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Utilizador {
    int idUtilizador;
    String password, nomeUtilizador, tipoUtilizador;

    /**
     * Contrutor completo
     *
     * @param idUtilizador
     * @param password
     * @param tipoUtilizador
     * @param nomeUtilizador
     */
    public Utilizador(int idUtilizador, String password, String tipoUtilizador, String nomeUtilizador) {
        this.idUtilizador = idUtilizador;
        this.password = password;
        this.tipoUtilizador = tipoUtilizador;
        this.nomeUtilizador = nomeUtilizador;
    }

    public Utilizador() {

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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Muda o valor:
     *
     * @param password set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Devolve:
     *
     * @return tipoUtilizador
     */
    public String getTipoUtilizador() {
        return tipoUtilizador;
    }

    /**
     * Muda o valor:
     *
     * @param tipoUtilizador set
     */
    public void setTipoUtilizador(String tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    /**
     * Devolve:
     *
     * @return nomeUtilizador
     */
    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    /**
     * Muda o valor:
     *
     * @param nomeUtilizador set
     */
    public void setNomeUtilizador(String nomeUtilizador) {
        this.nomeUtilizador = nomeUtilizador;
    }
}
