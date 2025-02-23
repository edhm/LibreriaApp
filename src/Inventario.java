import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Inventario {
    private List<Libro> libros;

    public Inventario() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public List<Libro> filtrarPorCategoria(String categoria) {
        return libros.stream()
                .filter(libro -> libro.getCategoria().getNombre().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    public List<Libro> ordenarPorPrecio() {
        return libros.stream()
                .sorted(Comparator.comparingDouble(Libro::getPrecio))
                .collect(Collectors.toList());
    }

    public List<Libro> ordenarPorStock() {
        return libros.stream()
                .sorted(Comparator.comparingInt(Libro::getStock))
                .collect(Collectors.toList());
    }

    public double calcularValorTotalInventario() {
        return libros.stream()
                .mapToDouble(libro -> libro.getPrecio() * libro.getStock())
                .sum();
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
