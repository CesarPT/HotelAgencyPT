package Classes;

import java.util.Date;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class RegistroEntrada {
    private int id_registro;
    private String local;
    private Date data;
    private int num_cartao;

    /**
     * Construtor completo
     *
     * @param id_registro
     * @param local
     * @param data
     * @param num_cartao
     */
    public RegistroEntrada(int id_registro, String local, Date data, int num_cartao) {
        this.id_registro = id_registro;
        this.local = local;
        this.data = data;
        this.num_cartao = num_cartao;
    }

    /**
     * Recebe:
     *
     * @return id_registro
     */
    public int getId_registro() {
        return id_registro;
    }

    /**
     * Recebe:
     *
     * @return local
     */
    public String getLocal() {
        return local;
    }

    /**
     * Recebe:
     *
     * @return data;
     */
    public Date getData() {
        return data;
    }

    /**
     * Recebe:
     *
     * @return num_cartao
     */
    public int getNum_cartao() {
        return num_cartao;
    }

    /**
     * Muda o valor:
     *
     * @param id_registro set
     */
    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    /**
     * Muda o valor:
     *
     * @param local set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Muda o valor:
     *
     * @param data set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Muda o valor
     *
     * @param num_cartao set
     */
    public void setNum_cartao(int num_cartao) {
        this.num_cartao = num_cartao;
    }
}
