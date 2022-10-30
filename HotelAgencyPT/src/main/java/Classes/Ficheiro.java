package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Ficheiro {
    private int id_ficheiro;
    private String descricao;

    /**
     * Construtor completo
     * @param id_ficheiro
     * @param descricao
     */
    public Ficheiro(int id_ficheiro, String descricao) {
        this.id_ficheiro = id_ficheiro;
        this.descricao = descricao;
    }

    /**
     * Recebe:
     * @return id_ficheiro
     */
    public int getId_ficheiro() {
        return id_ficheiro;
    }
    /**
     * Recebe:
     * @return descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Muda o valor:
     * @param id_ficheiro set
     */
    public void setId_ficheiro(int id_ficheiro) {
        this.id_ficheiro = id_ficheiro;
    }
    /**
     * Muda o valor:
     * @param descricao set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
