import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria {
    private Inventario inventario;
    private Scanner scanner;

    public Libreria() {
        this.inventario = new Inventario();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bienvenido a la Librería");
            System.out.println("1. Añadir Libro");
            System.out.println("2. Buscar Libro");
            System.out.println("3. Registrar Venta");
            System.out.println("4. Filtrar Libros por Categoría");
            System.out.println("5. Ordenar Libros por Precio");
            System.out.println("6. Ordenar Libros por Stock");
            System.out.println("7. Calcular Valor Total del Inventario");
            System.out.println("8. Mostrar Estadísticas");
            System.out.println("9. Listar Todos los Libros");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
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
                    filtrarLibrosPorCategoria();
                    break;
                case 5:
                    ordenarLibrosPorPrecio();
                    break;
                case 6:
                    ordenarLibrosPorStock();
                    break;
                case 7:
                    calcularValorTotalInventario();
                    break;
                case 8:
                    mostrarEstadisticas();
                    break;
                case 9:
                    listarLibros();
                    break;
                case 10:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            if (continuar) {
                System.out.print("¿Desea realizar otra operación? (s/n): ");
                String respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    continuar = false;
                    System.out.println("Saliendo...");
                }
            }
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

        Autor autor = new Autor(inventario.getLibros().size() + 1, nombreAutor, "");
        Categoria categoria = new Categoria(inventario.getLibros().size() + 1, nombreCategoria, "");
        Libro libro = new Libro(inventario.getLibros().size() + 1, titulo, autor, categoria, precio, stock);

        inventario.agregarLibro(libro);
        System.out.println("Libro añadido correctamente.");
    }

    private void buscarLibro() {
        System.out.print("Buscar libro por título: ");
        String titulo = scanner.nextLine();
        List<Libro> libros = inventario.getLibros().stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .toList();

        if (libros.isEmpty()) {
            System.out.println("Libro no encontrado.");
        } else {
            Reportes.mostrarListado(libros);
        }
    }

    private void registrarVenta() {
        System.out.print("ID del libro: ");
        int idLibro = scanner.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Libro libro = inventario.getLibros().stream()
                .filter(l -> l.getId() == idLibro)
                .findFirst()
                .orElse(null);

        if (libro != null && libro.getStock() >= cantidad) {
            libro.setStock(libro.getStock() - cantidad);
            System.out.println("Venta registrada.");
        } else {
            System.out.println("No hay suficiente stock.");
        }
    }

    private void filtrarLibrosPorCategoria() {
        System.out.print("Ingrese la categoría a filtrar: ");
        String categoria = scanner.nextLine();
        List<Libro> librosFiltrados = inventario.filtrarPorCategoria(categoria);
        Reportes.mostrarListado(librosFiltrados);
    }

    private void ordenarLibrosPorPrecio() {
        List<Libro> librosOrdenados = inventario.ordenarPorPrecio();
        Reportes.mostrarListado(librosOrdenados);
    }

    private void ordenarLibrosPorStock() {
        List<Libro> librosOrdenados = inventario.ordenarPorStock();
        Reportes.mostrarListado(librosOrdenados);
    }

    private void calcularValorTotalInventario() {
        double valorTotal = inventario.calcularValorTotalInventario();
        System.out.printf("Valor total del inventario: $%.2f\n", valorTotal);
    }

    private void mostrarEstadisticas() {
        Reportes.mostrarEstadisticas(inventario.getLibros());
    }

    private void listarLibros() {
        Reportes.mostrarListado(inventario.getLibros());
    }
}