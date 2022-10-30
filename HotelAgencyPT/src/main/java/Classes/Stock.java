package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Stock {
    private int id_item;
    private int quantidade;

    /**
     * Construtor completo
     * @param id_item
     * @param quantidade
     */
    public Stock(int id_item, int quantidade) {
        this.id_item = id_item;
        this.quantidade = quantidade;
    }

    /**
     * Recebe:
     * @return id_item
     */
    public int getId_item() {
        return id_item;
    }
    /**
     * Recebe:
     * @return quantidade;
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Muda o valor:
     * @param id_item set
     */
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }
    /**
     * Muda o valor:
     * @param quantidade set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
