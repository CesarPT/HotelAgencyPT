package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Item {
    private int id_item;
    private String nome_item;
    private String estado;

    /**
     * Construtor completo
     *
     * @param id_item
     * @param nome_item
     * @param estado
     */
    public Item(int id_item, String nome_item, String estado) {
        this.id_item = id_item;
        this.nome_item = nome_item;
        this.estado = estado;
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
     * @return nome_item
     */
    public String getNome_item() {
        return nome_item;
    }

    /**
     * Recebe:
     *
     * @return estado
     */
    public String getEstado() {
        return estado;
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
     * @param nome_item set
     */
    public void setNome_item(String nome_item) {
        this.nome_item = nome_item;
    }

    /**
     * Muda o valor:
     *
     * @param estado set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
