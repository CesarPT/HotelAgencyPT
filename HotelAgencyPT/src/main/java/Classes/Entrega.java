package Classes;

import java.util.Date;

public class Entrega {
    /**
     * Classe pública com todas os métodos usadas na aplicação
     */
    private String orderNumber;
    private Date data_entrega;
    private String party_identifier;
    private String empresa;
    private String  rua;
    private String  n_porta;
    private String  cidade;
    private String  cp;
    private String  pais;



    /**
     * Construtor completo
     *
     * @param orderNumber
     * @param rua
     * @param n_porta
     * @param cidade
     * @param cp
     * @param pais
     */
    public Entrega(String orderNumber,Date data_entrega, String party_identifier,String empresa,String rua,String n_porta,String cidade, String  cp,String  pais) {
        this.orderNumber = orderNumber;
        this.data_entrega = data_entrega;
        this.party_identifier = party_identifier;
        this.empresa = empresa;
        this.rua = rua;
        this.n_porta = n_porta;
        this.cidade = cidade;
        this.cp = cp;
        this.pais = pais;
    }
    /**
     * Construtor vazio
     */
    public Entrega() {}


    /**
     * Muda o valor:
     *
     * @param orderNumber set
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * Recebe:
     *
     * @return id_ph
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * Muda o valor:
     *
     * @param data_entrega set
     */
    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    /**
     * Recebe:
     *
     * @return data_entrega
     */
    public Date getData_entrega() {
        return data_entrega;
    }

    public String getParty_identifier() {
        return party_identifier;
    }

    public void setParty_identifier(String party_identifier) {
        this.party_identifier = party_identifier;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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
    public String getCidade() {return cidade;    }

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
    public String getPais() {return pais;   }

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
    public String getCp() {return cp;}
}


