package Classes;

import java.util.Date;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Cartao {
    private int numcartao;
    private Date datacriacao;
    private Date dataexp;

    /**
     * Construtor completo
     *
     * @param numcartao
     * @param datacriacao
     * @param dataexp
     */
    public Cartao(int numcartao, Date datacriacao, Date dataexp) {
        this.numcartao = numcartao;
        this.datacriacao = datacriacao;
        this.dataexp = dataexp;
    }

    //Construtor simples
    public Cartao() {
    }

    public int getNumcartao() {
        return numcartao;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public Date getDataexp() {
        return dataexp;
    }

    public void setNumcartao(int numcartao) {
        this.numcartao = numcartao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public void setDataexp(Date dataexp) {
        this.dataexp = dataexp;
    }
}
