import java.time.LocalDate;

public class Venta {
    private int id;
    private Libro libro;
    private int cantidad;
    private LocalDate fecha;

    public Venta(int id, Libro libro, int cantidad, LocalDate fecha) {
        this.id = id;
        this.libro = libro;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

}
