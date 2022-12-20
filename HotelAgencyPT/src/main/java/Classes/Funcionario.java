package Classes;

public class Funcionario {

    int idUtilizador, idFuncionario;
    String estado;

    /**
     * Contrutor completo
     *
     * @param idUtilizador
     * @param idFuncionario
     * @param estado
     */
    public Funcionario(int idUtilizador, int idFuncionario, String estado) {
        this.idUtilizador = idUtilizador;
        this.idFuncionario = idFuncionario;
        this.estado = estado;
    }

    public Funcionario() {

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
     * @return idFuncionario;
     */
    public int getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Muda o valor:
     *
     * @param idFuncionario set
     */
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Devolve:
     *
     * @return funcao
     */
    public String getFuncao() {
        return estado;
    }

    /**
     * Muda o valor:
     *
     * @param estado set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
