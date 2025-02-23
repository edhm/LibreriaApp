import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Reportes {
    public static void mostrarEstadisticas(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros para mostrar estadísticas.");
            return;
        }

        // Producto más caro
        Optional<Libro> libroMasCaro = libros.stream()
                .max(Comparator.comparingDouble(Libro::getPrecio));
        libroMasCaro.ifPresent(libro -> System.out.println("Libro más caro: " + libro.getTitulo() + " - $" + libro.getPrecio()));

        // Producto más barato
        Optional<Libro> libroMasBarato = libros.stream()
                .min(Comparator.comparingDouble(Libro::getPrecio));
        libroMasBarato.ifPresent(libro -> System.out.println("Libro más barato: " + libro.getTitulo() + " - $" + libro.getPrecio()));

        // Promedio de precios
        double promedioPrecios = libros.stream()
                .mapToDouble(Libro::getPrecio)
                .average()
                .orElse(0.0);
        System.out.printf("Promedio de precios: $%.2f\n", promedioPrecios);
    }

    public static void mostrarListado(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No hay libros para mostrar.");
        } else {
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
            System.out.println("| ID | Título                 | Autor                | Categoría         | Precio | Stock |");
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
            libros.forEach(System.out::println);
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
        }
    }
}