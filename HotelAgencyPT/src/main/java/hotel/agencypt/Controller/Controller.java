package hotel.agencypt.Controller;

/**
 * Classe pública para passar informações de um scene para outro scene
 */
public class Controller {
    private String username;
    private char tipo_user;
    private int piso;
    private int idquarto;
    private int selectedRowReserva;
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

    public char getTipo_user() {
        return tipo_user;
    }

    public void setTipo_user(char tipo_user) {
        this.tipo_user = tipo_user;
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

    public int getSelectedRowReserva() {
        return selectedRowReserva;
    }

    public void setSelectedRowReserva(int selectedRowReserva) {
        this.selectedRowReserva = selectedRowReserva;
    }
}
