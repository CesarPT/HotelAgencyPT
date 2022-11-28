package hotel.agencypt.Controller;

/**
 * Classe pública para passar informações de um scene para outro scene
 */
public class Controller {
    private String username;
    private int piso;
    private int idquarto;
    private final static Controller INSTANCE = new Controller();

    /**
     * Construtor padrão
     */
    private Controller() {
    }

    /**
     * Obter a instância
     *
     * @return INSTANCE
     */
    public static Controller getInstance() {
        return INSTANCE;
    }

    //------------PASSAR INFORMAÇÕES AQUI------------
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getIdQuarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }
}
