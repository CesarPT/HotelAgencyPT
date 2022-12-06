package Classes;

import java.util.Date;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Reserva {
    private int idreserva;
    private int idcliente;
    private int idquarto;
    private int idservico;
    private int numcartao;
    private Date datai;
    private Date dataf;

    /**
     * Construtor completo
     *
     * @param idreserva
     * @param idcliente
     * @param idquarto
«     * @param numcartao
     * @param datai
     * @param dataf
     */
    public Reserva(int idreserva, int idcliente, int idquarto,int idservico, int numcartao, Date datai, Date dataf) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idquarto = idquarto;
        this.idservico = idservico;
        this.numcartao = numcartao;
        this.datai = datai;
        this.dataf = dataf;
    }

    /**
     * Construtor simples
     */
    public Reserva() {

    }

    public int getIdreserva() {
        return idreserva;
    }

    public int getIdcliente() {
        return idcliente;
    }


    public int getIdquarto() {
        return idquarto;
    }


    public int getNumcartao() {
        return numcartao;
    }

    public int getIdservico() {
        return idservico;
    }

    public Date getDataI() {
        return datai;
    }

    public Date getDataF() {
        return dataf;
    }


    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }

    public void setIdservico(int idservico) {
        this.idservico = idservico;
    }

    public void setNumcartao(int numcartao) {
        this.numcartao = numcartao;
    }

    public void setDataI(Date datai) {
        this.datai = datai;
    }

    public void setDataF(Date dataf) {
        this.dataf = dataf;
    }

}
