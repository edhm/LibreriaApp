public class Autor {
    private int id;
    private String nombre;
    private String biografia;

    public Autor(int id, String nombre, String biografia) {
        this.id = id;
        this.nombre = nombre;
        this.biografia = biografia;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
