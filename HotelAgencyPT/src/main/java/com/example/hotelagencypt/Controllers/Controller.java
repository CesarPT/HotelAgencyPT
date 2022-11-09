package com.example.hotelagencypt.Controllers;

/**
 * Classe pública para passar informações de um scene para outro scene
 */
public class Controller {
    private int id_clube;
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

    //PASSAR INFORMAÇÕES AQUI


}
