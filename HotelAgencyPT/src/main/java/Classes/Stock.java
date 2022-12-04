package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Stock {
    private String product_identifier;
    private String product_description;
    private int quantidade;

    /**
     * Construtor completo
     *
     * @param product_identifier
     * @param product_description
     * @param quantidade
     */
    public Stock(String product_identifier,String product_description ,int quantidade) {
        this.product_identifier = product_identifier;
        this.product_description = product_description;
        this.quantidade = quantidade;
    }

    public Stock() {    }

    public void setProduct_identifier(String product_identifier) {
        this.product_identifier = product_identifier;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduct_identifier() {
        return product_identifier;
    }

    public String getProduct_description() {
        return product_description;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
