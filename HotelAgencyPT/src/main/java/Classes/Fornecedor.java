package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Fornecedor {
    private int id;
    private String rua;
    private String n_porta;
    private String cidade;
    private String  cp;
    private String  pais;

    /**
     * Construtor completo
     *
     * @param id
     * @param rua
     * @param n_porta
     * @param cidade
     * @param cp
     * @param pais
     */
    public Fornecedor(int id, String rua,String n_porta,String cidade, String  cp,String  pais) {
        this.id = id;
        this.rua = rua;
        this.n_porta = n_porta;
        this.cidade = cidade;
        this.cp = cp;
        this.pais = pais;
    }
    /**
     * Construtor vazio
     */
    public Fornecedor() {

    }


    /**
     * Muda o valor:
     *
     * @param id set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public int getId() {
        return id;
    }

    /**
     * Muda o valor:
     *
     * @param rua set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Recebe:
     *
     * @return rua
     */
    public String getRua() {
        return rua;
    }


    /**
     * Muda o valor:
     *
     * @param n_porta set
     */
    public void setN_porta(String n_porta) {
        this.n_porta = n_porta;
    }

    /**
     * Recebe:
     *
     * @return n_porta
     */
    public String getN_porta() {
        return n_porta;
    }

    /**
     * Muda o valor:
     *
     * @param cidade set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Recebe:
     *
     * @return cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Muda o valor:
     *
     * @param pais set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Recebe:
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Muda o valor:
     *
     * @param cp set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * Recebe:
     *
     * @return cp
     */
    public String getCp() {
        return cp;
    }
}
