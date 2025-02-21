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
        // Variable para controlar el bucle
        boolean continuar=true;
        while (continuar){
            // Menú principal
            System.out.println("Bienvenido a la Librería");
            System.out.println("1. Añadir Libro");
            System.out.println("2. Buscar Libro");
            System.out.println("3. Registrar Venta");
            System.out.println("4. Filtrar Libros");
            System.out.println("5. Listado de todos los Libros");
            System.out.println("6. Salir");
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
                    filtrarLibros();
                    break;
                case 5:
                    listarLibros();
                    break;
                case 6:
                    System.out.println("Saliendo ...");
                default:
                    System.out.println("Opción no válida,\nIntnente nuevamente");
            }

        // Preguntar al usuario si desea continuar (excepto si eligió salir)
        if (continuar){
            System.out.print("¿Desea realizar otra operación? (s/n):");
            String respuesta= scanner.nextLine();
            if(respuesta.equalsIgnoreCase("n")){
                continuar=false;
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
    private void filtrarLibros(){
        System.out.println("Filrar libros por:");
        System.out.println("1. Título");
        System.out.println("2. Autor");
        System.out.println("3. Categoría");
        System.out.println("Seleccione una opción");
        int opcionFiltro= scanner.nextInt();

        switch (opcionFiltro){
            case 1:
                System.out.println("Ingrese el Título a buscar");
                String titulo= scanner.nextLine();
                libros.stream()
                        .filter(libro ->libro.getTitulo().equalsIgnoreCase(titulo))
                        .forEach(System.out::println);
                break;
            case 2:
                System.out.println("Ingrese el nombre del autor");
                String nombreAutor=scanner.nextLine();
                libros.stream()
                        .filter(libro->libro.getAutor().getNombre().equalsIgnoreCase(nombreAutor))
                        .forEach(System.out::println);
                break;
            case 3:
                System.out.println("Ingrese el nombre de la categoría");
                String nombreCategoria = scanner.nextLine();
                libros.stream()
                        .filter(libro->libro.getCategoria().getNombre().equalsIgnoreCase(nombreCategoria))
                        .forEach(System.out::println);
            default:
                System.out.println("Opción no valida.");
        }
    }
    private void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el sistema.");
        } else {
            System.out.println("Listado de libros:");
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
            System.out.println("| ID | Título                 | Autor                | Categoría         | Precio | Stock |");
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
            libros.forEach(libro -> {
                System.out.printf("| %-2d | %-22s | %-20s | %-17s | %-6.2f | %-5d |\n",
                        libro.getId(),
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getCategoria().getNombre(),
                        libro.getPrecio(),
                        libro.getStock());
            });
            System.out.println("+----+------------------------+----------------------+-------------------+--------+-------+");
        }
    }
}
