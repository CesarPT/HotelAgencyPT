package Classes;

public class CheckIn {


    /**
     * Classe pública com todas os métodos usadas na aplicação
     */

    private Integer idreserva;
    private Integer idcliente;
    private Integer idquarto;
    private Integer idservico;
    private Integer numcartao;
    private String datai;
    private String dataf;
    private Integer idClient;
    private Integer floor;
    private String type;


    public CheckIn(Integer idreserva, Integer idquarto, String datai, String dataf) {
        this.idreserva = idreserva;
        this.idquarto = idquarto;
        this.datai = datai;
        this.dataf = dataf;
    }

    /**
     * Construtor completo
     *
     * @param idreserva
     * @param idcliente
     * @param idquarto  «     * @param numcartao
     * @param datai
     * @param dataf
     * @param idClient
     * @param floor
     * @param type
     */
    public CheckIn(int idreserva, Integer idcliente, Integer idquarto, Integer idservico, Integer numcartao, String datai, String dataf, int idClient, Integer floor, String type) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idquarto = idquarto;
        this.idservico = idservico;
        this.numcartao = numcartao;
        this.datai = datai;
        this.dataf = dataf;
        this.idClient = idClient;
        this.floor = floor;
        this.type = type;
    }

    public CheckIn() {

    }

    public Integer getIdreserva() {
        return idreserva;
    }

    public Integer getIdcliente() {
        return idcliente;
    }


    public Integer getIdquarto() {
        return idquarto;
    }


    public Integer getNumcartao() {
        return numcartao;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public String getDatai() {
        return datai;
    }

    public String getDataf() {
        return dataf;
    }

    public void setIdreserva(Integer queryReservID) {
        this.idreserva = idreserva;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdquarto(Integer idquarto) {
        this.idquarto = idquarto;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public void setNumcartao(Integer numcartao) {
        this.numcartao = numcartao;
    }

    public void setDatai(String datai) {
        this.datai = datai;
    }

    public void setDataf(String dataf) {
        this.dataf = dataf;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
