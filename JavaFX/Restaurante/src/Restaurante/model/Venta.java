package Restaurante.model;

public class Venta {

    private String mesa;
    private double importe;
    private String metodoPago;
    private String fecha;

    public Venta(String mesa, double importe, String metodoPago, String fecha) {
        this.mesa = mesa;
        this.importe = importe;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
    }

    // Getters
    public String getMesa() { return mesa; }
    public double getImporte() { return importe; }
    public String getMetodoPago() { return metodoPago; }
    public String getFecha() { return fecha; }
}
