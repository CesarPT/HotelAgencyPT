package Classes;

import javafx.scene.paint.Color;

public class QuartoStock {
    private String idstock;
    private int idquarto;
    private String product_description;
    private float quantidade;
    private String estado;


    public QuartoStock(String idstock, int idquarto, String productDescription, float quantidade) {
        this.idstock = idstock;
        this.idquarto = idquarto;
        product_description = productDescription;
        this.quantidade = quantidade;
    }
    public QuartoStock(int idquarto, String productDescription, float quantidade, String estado) {
        this.idquarto = idquarto;
        product_description = productDescription;
        this.quantidade = quantidade;
        this.estado = estado;
    }
    public QuartoStock() {

    }


    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public int getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }

    public String getIdstock() {
        return idstock;
    }

    public void setIdstock(String idstock) {
        this.idstock = idstock;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
