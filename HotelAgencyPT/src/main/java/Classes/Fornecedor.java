package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Fornecedor {
    private int id_ph;
    private String rua;
    private String n_porta;
    private String cidade;
    private String  cp;
    private String  pais;

    /**
     * Construtor completo
     *
     * @param id_ph
     * @param rua
     * @param n_porta
     * @param cidade
     * @param cp
     * @param pais
     */
    public Fornecedor(int id_ph, String rua,String n_porta,String cidade, String  cp,String  pais) {
        this.id_ph = id_ph;
        this.rua = rua;
        this.n_porta = n_porta;
        this.cidade = cidade;
        this.cp = cp;
        this.pais = pais;
    }



    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setId_ph(int id_ph) {
        this.id_ph = id_ph;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public int getId_ph() {
        return id_ph;
    }

    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public String getRua() {
        return rua;
    }


    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setN_porta(String n_porta) {
        this.n_porta = n_porta;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public String getN_porta() {
        return n_porta;
    }

    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public String getPais() {
        return pais;
    }

    /**
     * Muda o valor:
     *
     * @param id_ph set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public String getCp() {
        return cp;
    }
}
