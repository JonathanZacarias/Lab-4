package Lab4.bean;

import java.util.ArrayList;
import java.util.List;


public class ClienteVIP extends Usuario implements VIP {
    private List<Libro> librosPrestados;

   // Getters y setters
   public List<Libro> getLibrosPrestados() {
    return librosPrestados;
}

public void setLibrosPrestados(List<Libro> librosPrestados) {
    this.librosPrestados = librosPrestados;
}


    @Override
    public void agregarLibroPremium(Libro libro) {
        if (librosPrestados == null) {
            librosPrestados = new ArrayList<>();
        }

        if (!librosPrestados.contains(libro) && librosPrestados.size() < 5) {
            librosPrestados.add(libro);
            System.out.println("Libro agregado con éxito.");
        } else {
            System.out.println("No se pudo agregar el libro. Límite de libros alcanzado o ya prestado.");
        }
    }

    @Override
    public void definirHorarioEntrega(String horario) {
        // Lógica para definir horario de entrega para clientes VIP
    }

    // Otros métodos específicos para clientes VIP
}
