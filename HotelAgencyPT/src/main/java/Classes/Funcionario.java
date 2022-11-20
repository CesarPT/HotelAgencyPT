package Classes;

public class Funcionario {

    int idUtilizador, idFuncionario;
    String funcao;

    /**
     * Contrutor completo
     *
     * @param idUtilizador
     * @param idFuncionario
     * @param funcao
     */
    public Funcionario(int idUtilizador, int idFuncionario, String funcao) {
        this.idUtilizador = idUtilizador;
        this.idFuncionario = idFuncionario;
        this.funcao = funcao;
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
        return funcao;
    }

    /**
     * Muda o valor:
     *
     * @param funcao set
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
