package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Entrega {
    private int id_entrega;
    private int id_item;
    private int quantidade;

    /**
     * Construtor completo
     *
     * @param id_entrega
     * @param id_item
     * @param quantidade
     */
    public Entrega(int id_entrega, int id_item, int quantidade) {
        this.id_entrega = id_entrega;
        this.id_item = id_item;
        this.quantidade = quantidade;
    }

    /**
     * Recebe:
     *
     * @return id_entrega
     */
    public int getId_entrega() {
        return id_entrega;
    }

    /**
     * Recebe:
     *
     * @return id_item
     */
    public int getId_item() {
        return id_item;
    }

    /**
     * Recebe:
     *
     * @return quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Muda o valor:
     *
     * @param id_entrega set
     */
    public void setId_entrega(int id_entrega) {
        this.id_entrega = id_entrega;
    }

    /**
     * Muda o valor:
     *
     * @param id_item set
     */
    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    /**
     * Muda o valor:
     *
     * @param quantidade set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
