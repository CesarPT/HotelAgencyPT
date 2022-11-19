package hotel.agencypt.Controller;

/**
 * Classe pública para passar informações de um scene para outro scene
 */
public class Controller {
    private String username;

    private final static Controller INSTANCE = new Controller();

    /**
     * Construtor padrão
     */
    private Controller() {
    }
    /**
     * Obter a instância
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
}
