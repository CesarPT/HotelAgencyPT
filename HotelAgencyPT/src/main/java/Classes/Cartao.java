package Classes;

import java.util.Date;

public class Cartao {
    int numeroCartao;
    Date datacriacao;

    /**
     * Contrutor completo
     *
     * @param numeroCartao
     * @param datacriacao
     */
    public Cartao(int numeroCartao, Date datacriacao) {
        this.numeroCartao = numeroCartao;
        this.datacriacao = datacriacao;
    }

    /**
     * Devolve:
     *
     * @return numero do cartao
     */
    public int getNumeroCartao() {
        return numeroCartao;
    }

    /**
     * Muda o valor:
     *
     * @param numeroCartao set
     */
    public void setNumeroCartao(int numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    /**
     * Devolve:
     *
     * @return data de criaçao
     */
    public Date getDatacriacao() {
        return datacriacao;
    }

    /**
     * Muda o valor:
     *
     * @param datacriacao set
     */
    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }
}
