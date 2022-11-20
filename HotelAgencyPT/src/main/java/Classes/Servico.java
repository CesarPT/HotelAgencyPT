package Classes;

public class Servico {
    int idServico;
    String descricao;

    /**
     * Contrutor completo
     *
     * @param idServico
     * @param descricao
     */
    public Servico(int idServico, String descricao) {
        this.idServico = idServico;
        this.descricao = descricao;
    }

    /**
     * Devolve:
     *
     * @return id do Serviço
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * Muda o valor:
     *
     * @param idServico set
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Devolve:
     *
     * @return Descrição
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Muda o valor:
     *
     * @param descricao set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
