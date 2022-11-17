package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class Gestor {
    private int id_gestor;
    private int id_user;

    /**
     * Construtor completo
     * @param id_gestor
     * @param id_user
     */
    public Gestor(int id_gestor, int id_user){
        this.id_gestor = id_gestor;
        this.id_user = id_user;
    }

    /**
     * Receber:
     * @return id_gestor
     */
    public int getId_gestor() {
        return id_gestor;
    }
    /**
     * Receber:
     * @return id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * Muda o valor:
     * @param id_gestor set
     */
    public void setId_gestor(int id_gestor) {
        this.id_gestor = id_gestor;
    }
    /**
     * Muda o valor:
     * @param id_user set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}