package Classes;

/**
 * Classe pública com todas os métodos usadas na aplicação
 */
public class RegEntrada {
    private int numcartao;
    private String data;
    private String local;

    /**
     * Construtor completo
     * @param numcartao
     * @param data
     * @param local
     */
    public RegEntrada(int numcartao, String data, String local) {
        this.numcartao = numcartao;
        this.data = data;
        this.local = local;
    }

    /**
     * Construtor simples
     */
    public RegEntrada(){

    }

    public int getNumcartao() {
        return numcartao;
    }

    public String getData() {
        return data;
    }

    public String getLocal() {
        return local;
    }

    public void setNumcartao(int numcartao) {
        this.numcartao = numcartao;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
