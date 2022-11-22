package Classes;

public class Quarto {
    int idQuarto, idReserva;

    /**
     * Contrutor completo
     *
     * @param idQuarto
     * @param idReserva
     */
    public Quarto(int idQuarto, int idReserva) {
        this.idQuarto = idQuarto;
        this.idReserva = idReserva;
    }

    /**
     * Devolve:
     *
     * @return Id do quarto
     */
    public int getIdQuarto() {
        return idQuarto;
    }

    /**
     * Muda o valor:
     *
     * @param idQuarto set
     */
    public void setIdQuarto(int idQuarto) {
        this.idQuarto = idQuarto;
    }

    /**
     * Devolve:
     *
     * @return Id da reserva
     */
    public int getIdReserva() {
        return idReserva;
    }

    /**
     * Muda o valor:
     *
     * @param idReserva id da reserva
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }
}
