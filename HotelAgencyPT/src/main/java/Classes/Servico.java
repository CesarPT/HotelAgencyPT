package Classes;

public class Servico {
    int idServico;
    String descricao;
    float preco;
    String estado;

    /**
     * Contrutor completo
     *
     * @param idServico
     * @param descricao
     */
    public Servico(int idServico, String descricao, float preco) {
        this.idServico = idServico;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Contrutor vasio
     */
    public Servico() {
    }

    public Servico(String descricao, float preco, String estado) {
        this.descricao = descricao;
        this.preco = preco;
        this.estado=estado;
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


    /**
     * Devolve:
     *
     * @return id do Serviço
     */
    public float getPreco() {
        return preco;
    }

    /**
     * Muda o valor:
     *
     * @param preco set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
