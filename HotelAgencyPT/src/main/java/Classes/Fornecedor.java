package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Fornecedor {
    private int id_fornecedor;
    private String nome_fornecedor;
    private String email;
    private int telemovel;
    private String morada;

    /**
     * Construtor completo
     * @param id_fornecedor
     * @param nome_fornecedor
     * @param email
     * @param telemovel
     * @param morada
     */
    public Fornecedor(int id_fornecedor, String nome_fornecedor, String email, int telemovel, String morada) {
        this.id_fornecedor = id_fornecedor;
        this.nome_fornecedor = nome_fornecedor;
        this.email = email;
        this.telemovel = telemovel;
        this.morada = morada;
    }

    /**
     * Recebe:
     * @return id_fornecedor
     */
    public int getId_fornecedor() {
        return id_fornecedor;
    }
    /**
     * Recebe:
     * @return nome_fornecedor
     */
    public String getNome_fornecedor() {
        return nome_fornecedor;
    }
    /**
     * Recebe:
     * @return email
     */
    public String getEmail() {
        return email;
    }
    /**
     * Recebe:
     * @return telemovel
     */
    public int getTelemovel() {
        return telemovel;
    }
    /**
     * Recebe:
     * @return morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * Muda o valor:
     * @param id_fornecedor set
     */
    public void setId_fornecedor(int id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
    }
    /**
     * Muda o valor:
     * @param nome_fornecedor set
     */
    public void setNome_fornecedor(String nome_fornecedor) {
        this.nome_fornecedor = nome_fornecedor;
    }
    /**
     * Muda o valor:
     * @param email set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Muda o valor:
     * @param telemovel set
     */
    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
    /**
     * Muda o valor:
     * @param morada set
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }
}
