package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Stock {
    private String product_identifier;
    private String product_description;
    private int quantidade;
    private String tipo_qtd;
    private float preco;
    private float vat;
    private float preco_total;

    /**
     * Construtor completo
     *
     * @param product_identifier
     * @param product_description
     * @param quantidade
     * @param tipoQtd
     * @param preco
     * @param vat
     * @param precoTotal
     */
    public Stock(String product_identifier, String product_description, int quantidade, String tipoQtd, float preco, float vat, float precoTotal) {
        this.product_identifier = product_identifier;
        this.product_description = product_description;
        this.quantidade = quantidade;
        this.tipo_qtd = tipoQtd;
        this.preco = preco;
        this.vat = vat;
        this.preco_total = precoTotal;
    }

    public Stock() {
    }

    public void setProduct_identifier(String product_identifier) {
        this.product_identifier = product_identifier;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setTipo_qtd(String tipo_qtd) {
        this.tipo_qtd = tipo_qtd;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setVat(float vat) {
        this.vat = vat;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
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

    public String getTipo_qtd() {
        return tipo_qtd;
    }

    public float getPreco() {
        return preco;
    }

    public float getVat() {
        return vat;
    }

    public float getPreco_total() {
        return preco_total;
    }
}
