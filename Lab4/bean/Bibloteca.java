package Lab4.bean;

import java.util.List;
class Biblioteca {
    private List<Libro> listaLibros;
    private List<Revista> listaRevistas;
    private List<Usuario> listaUsuarios;
    private List<Prestamo> listaPrestamos;
    // Otros atributos y métodos relevantes

     // Getters y setters
    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public List<Revista> getListaRevistas() {
        return listaRevistas;
    }

    public void setListaRevistas(List<Revista> listaRevistas) {
        this.listaRevistas = listaRevistas;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public List<Prestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    // Otros métodos relevantes
}
