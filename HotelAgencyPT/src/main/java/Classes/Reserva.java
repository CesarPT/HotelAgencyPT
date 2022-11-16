package Classes;

public class Reserva {
    int idReserva, idCliente, idQuarto, idServico, numCartao;

    /**
     * Construtor completo
     *
     * @param idReserva
     * @param idCliente
     * @param idQuarto
     * @param idServico
     * @param numCartao
     */
    public Reserva(int idReserva, int idCliente, int idQuarto, int idServico, int numCartao) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idQuarto = idQuarto;
        this.idServico = idServico;
        this.numCartao = numCartao;
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
     * @param idReserva set
     */
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    /**
     * Devolve:
     *
     * @return Id do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Muda o valor:
     *
     * @param idCliente set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
     * @return Id de Serviço
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * Muda o valor:
     *
     * @param idServico set
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Devolve:
     *
     * @return Numero do cartão
     */
    public int getNumCartao() {
        return numCartao;
    }

    /**
     * Muda o valor:
     *
     * @param numCartao set
     */
    public void setNumCartao(int numCartao) {
        this.numCartao = numCartao;
    }
}
