import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria {

    private List<Libro> libros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Categoria> categorias = new ArrayList<>();
    private List<Venta> ventas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {

        // Menú principal
        System.out.println("Bienvenido a la Librería");
        System.out.println("1. Añadir Libro");
        System.out.println("2. Buscar Libro");
        System.out.println("3. Registrar Venta");
        System.out.println("4. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                añadirLibro();
                break;
            case 2:
                buscarLibro();
                break;
            case 3:
                registrarVenta();
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void añadirLibro() {
        System.out.println("Añadir Libro");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String nombreAutor = scanner.nextLine();
        System.out.print("Categoría: ");
        String nombreCategoria = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Autor autor = new Autor(autores.size() + 1, nombreAutor, "");
        Categoria categoria = new Categoria(categorias.size() + 1, nombreCategoria, "");
        Libro libro = new Libro(libros.size() + 1, titulo, autor, categoria, precio, stock);

        libros.add(libro);
        autores.add(autor);
        categorias.add(categoria);

        System.out.println("Libro añadido correctamente.");
    }

    private void buscarLibro() {
        System.out.print("Buscar libro por título: ");
        String titulo = scanner.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(libro);
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    private void registrarVenta() {
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Libro libro = libros.stream()
                .filter(l -> l.getId() == idLibro)
                .findFirst()
                .orElse(null);

        if (libro != null && libro.getStock() >= cantidad) {
            Venta venta = new Venta(ventas.size() + 1, libro, cantidad, java.time.LocalDate.now());
            ventas.add(venta);
            libro.setStock(libro.getStock() - cantidad);
            System.out.println("Venta registrada.");
        } else {
            System.out.println("No hay suficiente stock.");
        }
    }
}
