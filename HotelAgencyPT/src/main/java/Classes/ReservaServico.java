package Classes;

import java.util.Date;

public class ReservaServico {
    private int idreserva;
    private int idservico;

    public ReservaServico(int idreserva, int idservico) {
        this.idreserva = idreserva;
        this.idservico = idservico;
    }
    public ReservaServico() {}


    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdservico() {
        return idservico;
    }

    public void setIdservico(int idservico) {
        this.idservico = idservico;
    }
}
