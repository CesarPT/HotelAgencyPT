package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Feedback {
    private int id_feedback;
    private String descricao;
    private int id_cliente;
    private char tipofeedback;

    /**
     * Construtor completo
     *
     * @param id_feedback
     * @param descricao
     * @param id_cliente
     * @param tipofeedback
     */
    public Feedback(int id_feedback, String descricao, int id_cliente, char tipofeedback) {
        this.id_feedback = id_feedback;
        this.descricao = descricao;
        this.id_cliente = id_cliente;
        this.tipofeedback = tipofeedback;
    }

    public Feedback() {
    }

    public Feedback(Integer idcliente, String descricao) {
        this.id_cliente = idcliente;
        this.descricao = descricao;
    }

    /**
     * Recebe:
     *
     * @return id_feedback
     */
    public int getId_feedback() {
        return id_feedback;
    }

    /**
     * Recebe:
     *
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Recebe:
     *
     * @return id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * Recebe
     *
     * @return tipofeedback
     */
    public char getTipofeedback() {
        return tipofeedback;
    }

    /**
     * Muda o valor:
     *
     * @param id_feedback set
     */
    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    /**
     * Muda o valor:
     *
     * @param descricao set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Muda o valor:
     *
     * @param id_cliente set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * Muda o valor:
     *
     * @param tipofeedback set
     */
    public void setTipofeedback(char tipofeedback) {
        this.tipofeedback = tipofeedback;
    }
}
