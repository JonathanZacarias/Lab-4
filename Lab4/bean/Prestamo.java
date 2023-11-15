package Lab4.bean;

import java.util.List;

public class Prestamo {
    private Usuario usuario;
    private List<Libro> librosPrestados;
    private List<Revista> revistasPrestadas;
    // Otros atributos y métodos relevantes

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void setLibrosPrestados(List<Libro> librosPrestados) {
        this.librosPrestados = librosPrestados;
    }

    public List<Revista> getRevistasPrestadas() {
        return revistasPrestadas;
    }

    public void setRevistasPrestadas(List<Revista> revistasPrestadas) {
        this.revistasPrestadas = revistasPrestadas;
    }

    // Otros métodos relevantes
}