package Classes;

public class Quarto {
    int idQuarto, piso;
    String descricao;
    float preco;

    /**
     * Construtor completo
     * @param idQuarto
     * @param piso
     * @param descricao
     * @param preco
     */
    public Quarto(int idQuarto, int piso, String descricao, float preco){
        this.idQuarto = idQuarto;
        this.piso = piso;
        this.descricao = descricao;
        this.preco = preco;
    }

    /**
     * Construtor simples
     */
    public Quarto() {

    }

    /**
     * Devolve:
     * @return Id do quarto
     */
    public int getIdQuarto() {
        return idQuarto;
    }

    /**
     * Devolve:
     * @return piso
     */
    public int getPiso() {
        return piso;
    }

    /**
     * Devolve:
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Devolve:
     * @return preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * Muda o valor:
     * @param idQuarto set
     */
    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    /**
     * Muda o valor:
     * @param descricao set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Muda o valor:
     * @param piso set
     */
    public void setPiso(int piso) {
        this.piso = piso;
    }

    /**
     * Muda o valor:
     * @param preco set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }
}
